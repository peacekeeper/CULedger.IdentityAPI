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

import com.evernym.sdk.vcx.LibVcx;
import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.credentialDef.CredentialDefApi;
import com.evernym.sdk.vcx.schema.SchemaApi;
import com.evernym.sdk.vcx.vcx.VcxApi;

public class VcxInit {

	private static final Logger logger = LoggerFactory.getLogger(VcxInit.class);
	private static final Random random = new Random();

	private static final String POOL_NAME = "pool1";
	private static final String WALLET_CONFIG = "{ \"id\":\"" + "culedger" + "\", \"storage_type\":\"" + "default" + "\"}";
	private static final String WALLET_CREDENTIALS = "{ \"key\":\"culedger-key\" }";
	private static final String SCHEMA_DATA = "[\"cu\"]";

	private static Pool pool;
	private static Wallet wallet;

	static String schemaId;
	static String credDefId;
	static Integer schemaSeqNo;

	public static void init() {

	}

	static {

		try {

			// init indy

			/*		NativeLibrary.addSearchPath("indy", "./lib/");
			Library indy = Native.loadLibrary("indy", Library.class);

			NativeLibrary.addSearchPath("sovtoken", "./lib/");
			Library sovtoken = Native.loadLibrary("sovtoken", Library.class);*/

			/*		System.loadLibrary("indy");
			System.loadLibrary("sovtoken");*/

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
			ex.printStackTrace(System.err);
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

	/*
	 * Helper methods
	 */

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

	/*	private static String schemaIdFromSchemaData(String schemaData) {

		return VcxConfiguration.VCX_INSTITUTION_DID + ":" + "2" + ":" + schemaNameFromSchemaData(schemaData) + ":" + schemaVersionFromSchemaData(schemaData);
	}

	private static String credDefIdFromSchemaData(String schemaData) {

		return null;
	}*/
}
