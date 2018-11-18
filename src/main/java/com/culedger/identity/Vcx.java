package com.culedger.identity;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.evernym.sdk.vcx.LibVcx;
import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.connection.ConnectionApi;
import com.evernym.sdk.vcx.credential.CredentialApi;
import com.evernym.sdk.vcx.credentialDef.CredentialDefApi;
import com.evernym.sdk.vcx.issuer.IssuerApi;
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

	private static final String CU_DID = "SsPVi4HpA8jJx7wcTqCEQ4";
	private static final String CU_NAME = "testcu";

	private static String schemaId;
	private static String credentialDefId;
	private static Integer schemaSeqNo;

	static {

		/*		NativeLibrary.addSearchPath("indy", "./lib/");
		Library indy = Native.loadLibrary("indy", Library.class);

		NativeLibrary.addSearchPath("sovtoken", "./lib/");
		Library sovtoken = Native.loadLibrary("sovtoken", Library.class);*/

		/*		System.loadLibrary("indy");
		System.loadLibrary("sovtoken");*/

		try {

			// init

			LibVcx.init();
			Integer init = VcxApi.vcxInit("/opt/sovrin/vcxconfig.json").get();
			if (init == null || init.intValue() != 0) throw new RuntimeException("VCX init failed.");

			if (logger.isInfoEnabled()) logger.info("VCX initialized.");

			// create schema

			String sourceId = CU_DID;
			String schemaName = "mycuid";
			String version = "0.0." + random.nextInt(1000000);
			String schemaData = "[\"cu\"]";
			Integer schemaHandle = SchemaApi.schemaCreate(sourceId, schemaName, version, schemaData, 0).get();
			schemaId = SchemaApi.schemaGetSchemaId(schemaHandle).get();
			String schema = SchemaApi.schemaSerialize(schemaHandle).get();
			schemaSeqNo = 0;

			if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " got schema handle " + schemaHandle + " and schema ID " + schemaId + " and schema " + schema + " and schema seq no " + schemaSeqNo);

			// create credential definition

			String issuerId = sourceId;
			String credentialName = "culedger";
			String tag = "test." + random.nextInt(1000000);
			String config = "{\"support_revocation\":false}";
			Integer credentialDefHandle = CredentialDefApi.credentialDefCreate(sourceId, credentialName, schemaId, issuerId, tag, config, 0).get();
			credentialDefId = CredentialDefApi.credentialDefGetCredentialDefId(credentialDefHandle).get();
			String credentialDef = CredentialDefApi.credentialDefSerialize(credentialDefHandle).get();

			if (logger.isInfoEnabled()) logger.info("For issuer ID " + issuerId + " got credential def handle " + credentialDefHandle + " and credential def ID " + credentialDefId + " and credential def " + credentialDef);
		} catch (InterruptedException | ExecutionException | VcxException ex) {

			if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
			schemaId = null;
			credentialDefId = null;
			System.exit(-1);
		}
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

			MemberDidMapper.add(memberId, connectionHandle);

			if (logger.isInfoEnabled()) logger.info("Added member ID " + memberId + " with connection handle " + connectionHandle);



			// wait

			while (true) {

				Integer connectionGetStateResult = ConnectionApi.connectionGetState(connectionHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got state result " + connectionGetStateResult);

				if (connectionGetStateResult.intValue() != 2) break;

				Thread.sleep(500);

				Integer connectionWaitUpdateStateResult = ConnectionApi.vcxConnectionUpdateState(connectionHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For connection handle " + connectionHandle + " got update state result " + connectionWaitUpdateStateResult);

				Thread.sleep(500);
			}





			// create credential

			String credentialData = credentialData(CU_NAME);
			String credentialName = "mycuidcredential";
			Integer credentialHandle = IssuerApi.issuerCreateCredential(sourceId, credentialDefId, CU_DID, credentialData, credentialName, 0).get();

			if (logger.isInfoEnabled()) logger.info("For source ID " + sourceId + " and credential def ID " + credentialDefId + " and credential data " + credentialData + " got credential handle " + credentialHandle);

			// send credential offer

			Integer issuerSendCredentialOfferResult = IssuerApi.issuerSendcredentialOffer(credentialHandle, connectionHandle).get();

			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential offer result " + issuerSendCredentialOfferResult);

			// update state

			Integer credentialUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();

			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " got credential update state result " + credentialUpdateStateResult);




			// wait

			while (true) {

				Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + connectionHandle + " got state result " + credentialWaitGetStateResult);

				if (credentialWaitGetStateResult.intValue() != 2) break;

				Thread.sleep(500);

				Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult);

				Thread.sleep(500);
			}


			
			// send credential

			String issuerSendCredentialResult = IssuerApi.issuerSendCredential(credentialHandle, connectionHandle).get();

			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " and connection handle " + connectionHandle + " got issuer send credential result " + issuerSendCredentialResult);

			
			

			// update state

			Integer credentialUpdateStateResult2 = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();

			if (logger.isInfoEnabled()) logger.info("For credential handle " + credentialHandle + " got credential update state result " + credentialUpdateStateResult2);




			// wait

			while (true) {

				Integer credentialWaitGetStateResult = IssuerApi.issuerCredntialGetState(credentialHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + connectionHandle + " got state result " + credentialWaitGetStateResult);

				if (credentialWaitGetStateResult.intValue() != 3) break;

				Thread.sleep(500);

				Integer credentialWaitUpdateStateResult = IssuerApi.issuerCredntialUpdateState(credentialHandle).get();

				if (logger.isInfoEnabled()) logger.info("WAIT: For credential handle " + credentialHandle + " got credential update state result " + credentialWaitUpdateStateResult);

				Thread.sleep(500);
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

			Integer connectionHandle = MemberDidMapper.getAsConnectionHandle(memberId);

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
				"{'cu':'" + cuName + "'}";

		return credentialData.replace("'", "\"");
	}

	private static String requestedAttrs() {

		String requestedAttrs = 
				"[{'name':'cu'}]";

		return requestedAttrs.replace("'", "\"");
	}

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
						"  'cred_def_id': '" + credentialDefId + "',\n" +
						"  'claim_name': 'Credential',\n" +
						"  'claim_id': 'defaultCredentialId',\n" +
						"  'msg_ref_id': ''\n" +
						"}]";

		return credentialOffer.replace("'", "\"");
	}
}
