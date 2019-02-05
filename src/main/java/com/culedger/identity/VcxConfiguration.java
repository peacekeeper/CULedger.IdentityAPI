package com.culedger.identity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.model.CULedgerKeyPair;

public class VcxConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(VcxApi.class);

	public static final String VCX_INSTITUTION_DID_SEED;
	public static final String VCX_GENESIS_PATH;
	public static final String VCX_INSTITUTION_LOGO_URL;
	public static final String VCX_INSTITUTION_NAME;
	public static final String VCX_CREDENTIAL_NAME;
	public static final String VCX_CREDENTIAL_VALUE;
	public static final long VCX_TIMEOUT_CONNECTIONINVITE;
	public static final long VCX_TIMEOUT_CREDENTIALOFFER;
	public static final long VCX_TIMEOUT_CREDENTIALSEND;
	public static final long VCX_TIMEOUT_PROOFREQUEST;
	public static final String VCX_SCHEMA_ID;
	public static final String VCX_CREDDEF_ID;

	public static JSONObject jsonObjectVcxConfig;

	public static void init() {

	}

	static {

		String E_VCX_INSTITUTION_DID_SEED = System.getenv("VCX_INSTITUTION_DID_SEED");
		if (E_VCX_INSTITUTION_DID_SEED == null) throw new RuntimeException("Variable VCX_INSTITUTION_DID_SEED not set.");
		VCX_INSTITUTION_DID_SEED = E_VCX_INSTITUTION_DID_SEED;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_DID_SEED: " + VCX_INSTITUTION_DID_SEED);

		String E_VCX_GENESIS_PATH = System.getenv("VCX_GENESIS_PATH");
		if (E_VCX_GENESIS_PATH == null) throw new RuntimeException("Variable VCX_GENESIS_PATH not set.");
		VCX_GENESIS_PATH = E_VCX_GENESIS_PATH;
		if (logger.isInfoEnabled()) logger.info("VCX_GENESIS_PATH: " + VCX_GENESIS_PATH);

		String E_VCX_INSTITUTION_LOGO_URL = System.getenv("VCX_INSTITUTION_LOGO_URL");
		if (E_VCX_INSTITUTION_LOGO_URL == null) throw new RuntimeException("Variable VCX_INSTITUTION_LOGO_URL not set.");
		VCX_INSTITUTION_LOGO_URL = E_VCX_INSTITUTION_LOGO_URL;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_LOGO_URL: " + VCX_INSTITUTION_LOGO_URL);

		String E_VCX_INSTITUTION_NAME = System.getenv("VCX_INSTITUTION_NAME");
		if (E_VCX_INSTITUTION_NAME == null) throw new RuntimeException("Variable VCX_INSTITUTION_NAME not set.");
		VCX_INSTITUTION_NAME = E_VCX_INSTITUTION_NAME;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_NAME: " + VCX_INSTITUTION_NAME);

		String E_VCX_CREDENTIAL_NAME = System.getenv("VCX_CREDENTIAL_NAME");
		if (E_VCX_CREDENTIAL_NAME == null) throw new RuntimeException("Variable VCX_CREDENTIAL_NAME not set.");
		VCX_CREDENTIAL_NAME = E_VCX_CREDENTIAL_NAME;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDENTIAL_NAME: " + VCX_CREDENTIAL_NAME);

		String E_VCX_CREDENTIAL_VALUE = System.getenv("VCX_CREDENTIAL_VALUE");
		if (E_VCX_CREDENTIAL_VALUE == null) throw new RuntimeException("Variable VCX_CREDENTIAL_VALUE not set.");
		VCX_CREDENTIAL_VALUE = E_VCX_CREDENTIAL_VALUE;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDENTIAL_VALUE: " + VCX_CREDENTIAL_VALUE);

		String E_VCX_TIMEOUT_CONNECTIONINVITE = System.getenv("VCX_TIMEOUT_CONNECTIONINVITE");
		if (E_VCX_TIMEOUT_CONNECTIONINVITE == null) throw new RuntimeException("Variable VCX_TIMEOUT_CONNECTIONINVITE not set.");
		VCX_TIMEOUT_CONNECTIONINVITE = Long.parseLong(E_VCX_TIMEOUT_CONNECTIONINVITE);
		if (logger.isInfoEnabled()) logger.info("VCX_TIMEOUT_CONNECTIONINVITE: " + VCX_TIMEOUT_CONNECTIONINVITE);

		String E_VCX_TIMEOUT_CREDENTIALOFFER = System.getenv("VCX_TIMEOUT_CREDENTIALOFFER");
		if (E_VCX_TIMEOUT_CREDENTIALOFFER == null) throw new RuntimeException("Variable VCX_TIMEOUT_CREDENTIALOFFER not set.");
		VCX_TIMEOUT_CREDENTIALOFFER = Long.parseLong(E_VCX_TIMEOUT_CREDENTIALOFFER);
		if (logger.isInfoEnabled()) logger.info("VCX_TIMEOUT_CREDENTIALOFFER: " + VCX_TIMEOUT_CREDENTIALOFFER);

		String E_VCX_TIMEOUT_CREDENTIALSEND = System.getenv("VCX_TIMEOUT_CREDENTIALSEND");
		if (E_VCX_TIMEOUT_CREDENTIALSEND == null) throw new RuntimeException("Variable VCX_TIMEOUT_CREDENTIALSEND not set.");
		VCX_TIMEOUT_CREDENTIALSEND = Long.parseLong(E_VCX_TIMEOUT_CREDENTIALSEND);
		if (logger.isInfoEnabled()) logger.info("VCX_TIMEOUT_CREDENTIALSEND: " + VCX_TIMEOUT_CREDENTIALSEND);

		String E_VCX_TIMEOUT_PROOFREQUEST = System.getenv("VCX_TIMEOUT_PROOFREQUEST");
		if (E_VCX_TIMEOUT_PROOFREQUEST == null) throw new RuntimeException("Variable VCX_TIMEOUT_PROOFREQUEST not set.");
		VCX_TIMEOUT_PROOFREQUEST = Long.parseLong(E_VCX_TIMEOUT_PROOFREQUEST);
		if (logger.isInfoEnabled()) logger.info("VCX_TIMEOUT_PROOFREQUEST: " + VCX_TIMEOUT_PROOFREQUEST);

		String E_VCX_SCHEMA_ID = System.getenv("VCX_SCHEMA_ID");
		if (E_VCX_SCHEMA_ID != null && E_VCX_SCHEMA_ID.trim().isEmpty()) E_VCX_SCHEMA_ID = null;
		VCX_SCHEMA_ID = E_VCX_SCHEMA_ID;
		if (logger.isInfoEnabled()) logger.info("VCX_SCHEMA_ID: " + VCX_SCHEMA_ID);

		String E_VCX_CREDDEF_ID = System.getenv("VCX_CREDDEF_ID");
		if (E_VCX_CREDDEF_ID != null && E_VCX_CREDDEF_ID.trim().isEmpty()) E_VCX_CREDDEF_ID = null;
		VCX_CREDDEF_ID = E_VCX_CREDDEF_ID;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDDEF_ID: " + VCX_CREDDEF_ID);

		try {

			jsonObjectVcxConfig = new JSONObject(IOUtils.toString(new InputStreamReader(new FileInputStream(new File("/opt/sovrin/vcxconfig.json")), StandardCharsets.UTF_8)));
			if (logger.isInfoEnabled()) logger.info("jsonObjectVcxConfig: " + jsonObjectVcxConfig.toString());
		} catch (Exception ex) {

			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	static List<CULedgerKeyPair> makeCULedgerKeyPairs() {

		List<CULedgerKeyPair> c = new ArrayList<CULedgerKeyPair> ();

		c.add(makeCULedgerKeyPair("VCX_INSTITUTION_DID_SEED", VCX_INSTITUTION_DID_SEED));
		c.add(makeCULedgerKeyPair("VCX_GENESIS_PATH", VCX_GENESIS_PATH));
		c.add(makeCULedgerKeyPair("VCX_INSTITUTION_LOGO_URL", VCX_INSTITUTION_LOGO_URL));
		c.add(makeCULedgerKeyPair("VCX_INSTITUTION_NAME", VCX_INSTITUTION_NAME));
		c.add(makeCULedgerKeyPair("VCX_CREDENTIAL_NAME", VCX_CREDENTIAL_NAME));
		c.add(makeCULedgerKeyPair("VCX_CREDENTIAL_VALUE", VCX_CREDENTIAL_VALUE));
		c.add(makeCULedgerKeyPair("VCX_TIMEOUT_CONNECTIONINVITE", "" + VCX_TIMEOUT_CONNECTIONINVITE));
		c.add(makeCULedgerKeyPair("VCX_TIMEOUT_CREDENTIALOFFER", "" + VCX_TIMEOUT_CREDENTIALOFFER));
		c.add(makeCULedgerKeyPair("VCX_TIMEOUT_CREDENTIALSEND", "" + VCX_TIMEOUT_CREDENTIALSEND));
		c.add(makeCULedgerKeyPair("VCX_TIMEOUT_PROOFREQUEST", "" + VCX_TIMEOUT_PROOFREQUEST));
		c.add(makeCULedgerKeyPair("VCX_SCHEMA_ID", VCX_SCHEMA_ID));
		c.add(makeCULedgerKeyPair("VCX_CREDDEF_ID", VCX_CREDDEF_ID));

		c.add(makeCULedgerKeyPair("schemaId", VcxInit.schemaId));
		c.add(makeCULedgerKeyPair("credDefId", VcxInit.credDefId));
		c.add(makeCULedgerKeyPair("schemaSeqNo", "" + VcxInit.schemaSeqNo));

		Iterator<?> i = jsonObjectVcxConfig.keys();
		while (i.hasNext()) {

			String key = (String) i.next();
			String value = jsonObjectVcxConfig.optString(key);

			c.add(makeCULedgerKeyPair("VCX_CONFIG_" + key, value));
		}

		return c;
	}

	private static CULedgerKeyPair makeCULedgerKeyPair(String valueName, String value) {

		CULedgerKeyPair c = new CULedgerKeyPair();
		c.setValueName(valueName);
		c.setValue(value);

		return c;
	}
}
