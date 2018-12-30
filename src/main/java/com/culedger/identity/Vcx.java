package com.culedger.identity;
import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.hyperledger.indy.sdk.IndyException;
import org.hyperledger.indy.sdk.LibIndy;
import org.hyperledger.indy.sdk.ledger.Ledger;
import org.hyperledger.indy.sdk.pool.Pool;
import org.hyperledger.indy.sdk.pool.PoolJSONParameters.OpenPoolLedgerJSONParameter;
import org.hyperledger.indy.sdk.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.culedger.identity.didmapper.DbMemberDidMapper;
import com.culedger.identity.didmapper.MemberDidMapper;
import com.culedger.identity.didmapper.MemoryMemberDidMapper;
import com.evernym.sdk.vcx.LibVcx;
import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.connection.ConnectionApi;
import com.evernym.sdk.vcx.credentialDef.CredentialDefApi;
import com.evernym.sdk.vcx.issuer.IssuerApi;
import com.evernym.sdk.vcx.proof.GetProofResult;
import com.evernym.sdk.vcx.proof.ProofApi;
import com.evernym.sdk.vcx.schema.SchemaApi;
import com.evernym.sdk.vcx.vcx.VcxApi;

import io.swagger.model.CULedgerMember;
import io.swagger.model.CULedgerOnboardingData;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class Vcx {

	private static final JSONParser jsonParser = new JSONParser(JSONParser.MODE_STRICTEST);
	private static final Logger logger = LoggerFactory.getLogger(Vcx.class);
	private static final Random random = new Random();

	public static final int VCX_UNDEFINED = 0;
	public static final int VCX_INITIALIZED = 1;
	public static final int VCX_OFFERSENT = 2;
	public static final int VCX_REQUESTRECEIVED = 3;
	public static final int VCX_ACCEPTED = 4;
	public static final int VCX_UNFULFILLED = 5;
	public static final int VCX_EXPIRED = 6;
	public static final int VCX_REVOKED = 7;

	public static final int PROOFSTATE_UNDEFINED = 0;
	public static final int PROOFSTATE_VERIFIED = 1;
	public static final int PROOFSTATE_INVALID = 2;

	private static final String POOL_NAME = "pool1";
	private static final String WALLET_CONFIG = "{ \"id\":\"" + "culedger" + "\", \"storage_type\":\"" + "default" + "\"}";
	private static final String WALLET_CREDENTIALS = "{ \"key\":\"culedger-key\" }";
	private static final String SCHEMA_DATA = "[\"cu\"]";

	private static Pool pool;
	private static Wallet wallet;

	private static MemberDidMapper memberDidMapper;

	private static String schemaId;
	private static String credDefId;
	private static Integer schemaSeqNo;

	static {

		/*		NativeLibrary.addSearchPath("indy", "./lib/");
		Library indy = Native.loadLibrary("indy", Library.class);

		NativeLibrary.addSearchPath("sovtoken", "./lib/");
		Library sovtoken = Native.loadLibrary("sovtoken", Library.class);*/

		/*		System.loadLibrary("indy");
		System.loadLibrary("sovtoken");*/

		try {

			VcxConfiguration.init();

			// init indy

			initLibIndy();

			Boolean schemaExists = checkSchemaExists(VcxConfiguration.VCX_SCHEMA_ID);
			if (logger.isInfoEnabled()) logger.info("Schema exists? " + schemaExists);

			Boolean credDefExists = checkCredDefExists(VcxConfiguration.VCX_CREDDEF_ID);
			if (logger.isInfoEnabled()) logger.info("CredDef exists? " + credDefExists);

			closeLibIndy();

			// init vcx

			initLibVcx();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {

				try {

					closeLibVcx();
				} catch (VcxException ex) {

					throw new RuntimeException(ex.getMessage(), ex);
				}
			}));

			// create schema and creddef

			if (VcxConfiguration.VCX_SCHEMA_ID != null) schemaId = VcxConfiguration.VCX_SCHEMA_ID; else schemaId = initCreateSchema(); 
			if (VcxConfiguration.VCX_CREDDEF_ID != null) credDefId = VcxConfiguration.VCX_CREDDEF_ID; else credDefId = initCreateCredDef(); 
		} catch (Exception ex) {

			if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
			schemaId = null;
			credDefId = null;
			System.exit(-1);
		}
	}

	private static void initLibIndy() throws IndyException, InterruptedException, ExecutionException {

		if (! LibIndy.isInitialized()) LibIndy.init(new File("./lib/libindy.so"));
		Pool.setProtocolVersion(2);

		pool = Pool.openPoolLedger(POOL_NAME, new OpenPoolLedgerJSONParameter(null, null).toJson()).get();
		if (logger.isInfoEnabled()) logger.info("pool: " + pool);

		wallet = Wallet.openWallet(WALLET_CONFIG, WALLET_CREDENTIALS).get();
		if (logger.isInfoEnabled()) logger.info("wallet: " + wallet);
	}

	private static void initLibVcx() throws InterruptedException, ExecutionException, VcxException {

		LibVcx.init();
		Integer init = VcxApi.vcxInit("/opt/sovrin/vcxconfig.json").get();
		if (init == null || init.intValue() != 0) throw new RuntimeException("VCX init failed.");

		if (logger.isInfoEnabled()) logger.info("VCX initialized.");

		// create member->DID mapper

		if (VcxConfiguration.VCX_DID_MAPPER.equals("memory")) {

			memberDidMapper = new MemoryMemberDidMapper();
		} else if (VcxConfiguration.VCX_DID_MAPPER.equals("db")) {

			memberDidMapper = new DbMemberDidMapper();
		} else {

			throw new IllegalArgumentException("Unknown DID->mapper type.");
		}
	}

	private static void closeLibIndy() throws InterruptedException, ExecutionException, IndyException {

		wallet.closeWallet().get();
		pool.closePoolLedger().get();

		if (logger.isInfoEnabled()) logger.info("LibIndy closed");
	}

	private static void closeLibVcx() throws VcxException {

		VcxApi.vcxShutdown(Boolean.FALSE);

		if (logger.isInfoEnabled()) logger.info("LibVcx closed");
	}

	private static Boolean checkSchemaExists(String schemaId) throws IndyException, InterruptedException, ExecutionException {

		if (schemaId == null) return null;

		String submitterDid = VcxConfiguration.VCX_INSTITUTION_DID;

		String getSchemaRequestJson = Ledger.buildGetSchemaRequest(submitterDid, schemaId).get();
		if (logger.isInfoEnabled()) logger.info("getSchemaRequestJson: " + getSchemaRequestJson);
		String getSchemaRequestResult = Ledger.signAndSubmitRequest(pool, wallet, submitterDid, getSchemaRequestJson).get();
		if (logger.isInfoEnabled()) logger.info("getSchemaRequestResult: " + getSchemaRequestResult);

		return Boolean.valueOf(getSchemaRequestResult != null);
	}

	private static Boolean checkCredDefExists(String credDefId) throws InterruptedException, ExecutionException, IndyException {

		if (credDefId == null) return null;

		String submitterDid = VcxConfiguration.VCX_INSTITUTION_DID;

		String getCredDefRequestJson = Ledger.buildGetCredDefRequest(submitterDid, credDefId).get();
		if (logger.isInfoEnabled()) logger.info("getCredDefRequestJson: " + getCredDefRequestJson);
		String getCredDefRequestResult = Ledger.signAndSubmitRequest(pool, wallet, submitterDid, getCredDefRequestJson).get();
		if (logger.isInfoEnabled()) logger.info("getCredDefRequestResult: " + getCredDefRequestResult);

		return Boolean.valueOf(getCredDefRequestResult != null);
	}

	private static String initCreateSchema() throws InterruptedException, ExecutionException, VcxException {

		String sourceId = VcxConfiguration.VCX_INSTITUTION_DID;
		String schemaName = schemaNameFromSchemaData(SCHEMA_DATA);
		String version = schemaVersionFromSchemaData(SCHEMA_DATA);
		String schemaData = SCHEMA_DATA;
		Integer schemaHandle = SchemaApi.schemaCreate(sourceId, schemaName, version, schemaData, 0).get();
		String schemaId = SchemaApi.schemaGetSchemaId(schemaHandle).get();
		String schema = SchemaApi.schemaSerialize(schemaHandle).get();
		schemaSeqNo = 0;

		if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " got schema handle " + schemaHandle + " and schema ID " + schemaId + " and schema " + schema + " and schema seq no " + schemaSeqNo);

		return schemaId;
	}

	private static String initCreateCredDef() throws InterruptedException, ExecutionException, VcxException {

		String sourceId = VcxConfiguration.VCX_INSTITUTION_DID;
		String issuerId = VcxConfiguration.VCX_INSTITUTION_DID;
		String credDefName = credDefName();
		String credDefTag = credDefTag();
		String config = "{\"support_revocation\":false}";
		Integer credentialDefHandle = CredentialDefApi.credentialDefCreate(sourceId, credDefName, schemaId, issuerId, credDefTag, config, 0).get();
		String credDefId = CredentialDefApi.credentialDefGetCredentialDefId(credentialDefHandle).get();
		String credDef = CredentialDefApi.credentialDefSerialize(credentialDefHandle).get();

		if (logger.isInfoEnabled()) logger.info("For issuer ID " + issuerId + " got creddef handle " + credentialDefHandle + " and creddef ID " + credDefId + " and creddef " + credDef);

		return credDefId;
	}

	public static ResponseEntity<CULedgerOnboardingData> memberOnBoard(String memberId, CULedgerOnboardingData cuLedgerOnboardingData) {

		try {

			// create connection

			String sourceId = memberId;
			Integer connectionHandle = ConnectionApi.vcxConnectionCreate(sourceId).get();
			if (connectionHandle == null) throw new NullPointerException();

			if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " created connection handle " + connectionHandle);

			// connect

			String memberPhoneNumber = cuLedgerOnboardingData.getMemberPhoneNumber();
			JSONObject jsonConnectionType = new JSONObject();
			jsonConnectionType.put("connection_type", "SMS");
			jsonConnectionType.put("phone", memberPhoneNumber);
			String connectionType = jsonConnectionType.toJSONString();
			String connectionDetails = ConnectionApi.vcxConnectionConnect(connectionHandle, connectionType).get();

			if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " and connection type " + connectionType + " created connection details " + connectionDetails);

			// update connection

			Integer connectionUpdateStateResult = ConnectionApi.vcxConnectionUpdateState(connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " got update state result " + connectionUpdateStateResult);

			// invite details

			String connectionInviteDetails = ConnectionApi.connectionInviteDetails(connectionHandle, 0).get();
			if (logger.isInfoEnabled()) logger.info("For connection handle " + connectionHandle + " got invite details " + connectionInviteDetails);

			JSONObject jsonConnectionInviteDetails = (JSONObject) jsonParser.parse(connectionInviteDetails);
			JSONObject jsonSenderDetail = jsonConnectionInviteDetails == null ? null : (JSONObject) jsonConnectionInviteDetails.get("senderDetail");
			JSONObject jsonAagentKeyDlgProof = jsonSenderDetail == null ? null : (JSONObject) jsonSenderDetail.get("agentKeyDlgProof");
			String agentDID = jsonAagentKeyDlgProof == null ? null : (String) jsonAagentKeyDlgProof.get("agentDID");

			if (logger.isInfoEnabled()) logger.info("For connection invite details " + connectionInviteDetails + " got agent DID " + agentDID);

			// write to member DID mapper

			memberDidMapper.add(memberId, connectionHandle);
			if (logger.isInfoEnabled()) logger.info("Added member ID " + memberId + " with connection handle " + connectionHandle);

			// wait

			long waitConnectionInvite = System.currentTimeMillis();
			while (true) {

				Integer connectionGetStateResult = ConnectionApi.connectionGetState(connectionHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got state result " + connectionGetStateResult);

				if (connectionGetStateResult.intValue() != VCX_OFFERSENT) break;

				Thread.sleep(1000);
				long timeout = VcxConfiguration.VCX_TIMEOUT_CONNECTIONINVITE - (System.currentTimeMillis() - waitConnectionInvite) / 1000;

				Integer connectionWaitUpdateStateResult = ConnectionApi.vcxConnectionUpdateState(connectionHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got update state result " + connectionWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");

				if (timeout <= 0) throw new InterruptedException("Timeout while waiting for connection.");
			}

			// create credential

			String credentialData = credentialData(VcxConfiguration.VCX_CREDENTIAL_VALUE);
			String credentialName = VcxConfiguration.VCX_CREDENTIAL_NAME;
			Integer credentialHandle = IssuerApi.issuerCreateCredential(sourceId, credDefId, null, credentialData, credentialName, "0").get();

			if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " and credential def ID " + credDefId + " and credential data " + credentialData + " got credential handle " + credentialHandle);

			// send credential offer

			Integer issuerSendCredentialOfferResult = IssuerApi.issuerSendcredentialOffer(credentialHandle, connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential offer result " + issuerSendCredentialOfferResult);

			// update state

			Integer credentialUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " got credential update state result " + credentialUpdateStateResult);

			// wait

			long waitCredentialOffer = System.currentTimeMillis();
			while (true) {

				Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + connectionHandle + " got state result " + credentialWaitGetStateResult);

				if (credentialWaitGetStateResult.intValue() != VCX_OFFERSENT) break;

				Thread.sleep(1000);
				long timeout = VcxConfiguration.VCX_TIMEOUT_CREDENTIALOFFER - (System.currentTimeMillis() - waitCredentialOffer) / 1000;

				Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");

				if (timeout <= 0) throw new InterruptedException("Timeout while waiting for credential offer.");
			}

			// send credential

			Integer issuerSendCredentialResult = IssuerApi.issuerSendCredential(credentialHandle, connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential result " + issuerSendCredentialResult);

			// update state

			Integer credentialUpdateStateResult2 = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " got credential update state result " + credentialUpdateStateResult2);

			// wait

			long waitCredentialSend = System.currentTimeMillis();
			while (true) {

				Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + credentialHandle + " got state result " + credentialWaitGetStateResult);

				if (credentialWaitGetStateResult.intValue() != VCX_REQUESTRECEIVED) break;

				Thread.sleep(1000);
				long timeout = VcxConfiguration.VCX_TIMEOUT_CREDENTIALSEND - (System.currentTimeMillis() - waitCredentialSend) / 1000;

				Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");

				if (timeout <= 0) throw new InterruptedException("Timeout while waiting for credential send.");
			}

			// done

			CULedgerOnboardingData cuLedgerOnboardingDataResponse = cuLedgerOnboardingData;
			return new ResponseEntity<CULedgerOnboardingData>(cuLedgerOnboardingDataResponse, HttpStatus.OK);
		} catch (Exception ex) {

			if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
			return new ResponseEntity<CULedgerOnboardingData>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static ResponseEntity<CULedgerMember> memberAuthenticate(String memberId) {

		try {

			// read from member DID mapper

			Integer connectionHandle = memberDidMapper.getAsConnectionHandle(memberId);
			if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " got connection handle " + connectionHandle);

			if (connectionHandle == null) return new ResponseEntity<CULedgerMember>(HttpStatus.NOT_FOUND);

			// create proof

			String sourceId = memberId;
			String requestedAttrs = requestedAttrs();
			String requestedPredicates = "";
			String proofName = "mycuidproof";
			Integer proofHandle = ProofApi.proofCreate(sourceId, requestedAttrs, requestedPredicates, proofName).get();

			if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " and requested attrs " + requestedAttrs + " and requested predicates " + requestedPredicates + " got proof handle " + proofHandle);

			// send proof request

			Integer proofSendRequestResult = ProofApi.proofSendRequest(proofHandle, connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " and connection handle " + connectionHandle + " got proof send request result " + proofSendRequestResult);

			// update state

			Integer proofUpdateStateResult = ProofApi.proofUpdateState(proofHandle).get();
			if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " got proof update state result " + proofUpdateStateResult);

			// wait

			long waitProofRequest = System.currentTimeMillis();
			while (true) {

				Integer proofWaitGetStateResult = ProofApi.proofGetState(proofHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For proof handle " + connectionHandle + " got state result " + proofWaitGetStateResult);

				if (proofWaitGetStateResult.intValue() != VCX_OFFERSENT) break;

				Thread.sleep(1000);
				long timeout = VcxConfiguration.VCX_TIMEOUT_PROOFREQUEST - (System.currentTimeMillis() - waitProofRequest) / 1000;

				Integer proofWaitUpdateStateResult = ProofApi.proofUpdateState(proofHandle).get();
				if (logger.isInfoEnabled()) logger.info("WAIT: For proof handle " + proofHandle + " got proof update state result " + proofWaitUpdateStateResult + " (TIMEOUT: " + timeout + ")");

				if (timeout <= 0) throw new InterruptedException("Timeout while waiting for proof.");
			}

			// get proof

			GetProofResult getProofResult = ProofApi.getProof(proofHandle, connectionHandle).get();
			if (logger.isInfoEnabled()) logger.info("For proof handle " + proofHandle + " and connection handle " + connectionHandle + " got proof result " + getProofResult + " with response data " + getProofResult.getResponse_data() + " and proof state " + getProofResult.getProof_state());

			if (getProofResult.getProof_state() != PROOFSTATE_VERIFIED) {

				return new ResponseEntity<CULedgerMember>(HttpStatus.UNAUTHORIZED);
			}

			// done

			CULedgerMember cuLedgerMember = new CULedgerMember();
			cuLedgerMember.setMemberId(memberId);
			return new ResponseEntity<CULedgerMember>(cuLedgerMember, HttpStatus.OK);
		} catch (Exception ex) {

			if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
			return new ResponseEntity<CULedgerMember>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private static String credentialData(String cuName) {

		String credentialData =
				"{'cu':['" + cuName + "']}";

		return credentialData.replace("'", "\"");
	}

	private static String requestedAttrs() {

		String requestedAttrs = 
				"[{'name':'cu'}]";

		return requestedAttrs.replace("'", "\"");
	}

	private static String schemaNameFromSchemaData(String schemaData) {

		return "mycuid-" + random.nextInt(1000000);
		//		return "mycuid-" + DigestUtils.sha256Hex(schemaData);
	}

	private static String schemaVersionFromSchemaData(String schemaData) {

		return "0.0.1";
	}

	private static String credDefName() {

		return "culedger-" + random.nextInt(1000000);
	}

	private static String credDefTag() {

		return "tag-" + random.nextInt(1000000);
	}
	/*
	private static String schemaIdFromSchemaData(String schemaData) {

		return VcxConfiguration.VCX_INSTITUTION_DID + ":" + "2" + ":" + schemaNameFromSchemaData(schemaData) + ":" + schemaVersionFromSchemaData(schemaData);
	}

	private static String credDefIdFromSchemaData(String schemaData) {

		return null;
	}*/

	private static String credentialOffer(String toDid, String fromDid, String cuValue) {

		String credentialOffer = 
				"[{\n" +
						"  'msg_type': 'CLAIM_OFFER',\n" +
						"  'version': '0.1',\n" +
						"  'to_did': '" + toDid + "',\n" +
						"  'from_did': '" + fromDid + "',\n" +
						"  'libindy_offer': '{}',\n" +
						"  'credential_attrs': {\n" +
						"    'cu': [\n" +
						"      '" + cuValue + "'\n" +
						"    ]\n" +
						"  },\n" +
						"  'schema_seq_no': " + schemaSeqNo + ",\n" +
						"  'cred_def_id': '" + credDefId + "',\n" +
						"  'claim_name': 'Credential',\n" +
						"  'claim_id': 'defaultCredentialId',\n" +
						"  'msg_ref_id': ''\n" +
						"}]";

		return credentialOffer.replace("'", "\"");
	}
}
