package com.culedger.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VcxConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(Vcx.class);

	public static final String VCX_INSTITUTION_LOGO_URL;
	public static final String VCX_INSTITUTION_NAME;
	public static final String VCX_INSTITUTION_DID;
	public static final String VCX_INSTITUTION_VERKEY;
	public static final String VCX_CREDENTIAL_NAME;
	public static final String VCX_CREDENTIAL_VALUE;
	public static final String VCX_DID_MAPPER;
	public static final String VCX_SCHEMA_ID;
	public static final String VCX_CREDDEF_ID;

	static {

		String E_VCX_INSTITUTION_LOGO_URL = System.getenv("VCX_INSTITUTION_LOGO_URL");
		if (E_VCX_INSTITUTION_LOGO_URL == null) throw new RuntimeException("Variable VCX_INSTITUTION_LOGO_URL not set.");
		VCX_INSTITUTION_LOGO_URL = E_VCX_INSTITUTION_LOGO_URL;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_LOGO_URL: " + VCX_INSTITUTION_LOGO_URL);

		String E_VCX_INSTITUTION_NAME = System.getenv("VCX_INSTITUTION_NAME");
		if (E_VCX_INSTITUTION_NAME == null) throw new RuntimeException("Variable VCX_INSTITUTION_NAME not set.");
		VCX_INSTITUTION_NAME = E_VCX_INSTITUTION_NAME;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_NAME: " + VCX_INSTITUTION_NAME);

		String E_VCX_INSTITUTION_DID = System.getenv("VCX_INSTITUTION_DID");
		if (E_VCX_INSTITUTION_DID == null) throw new RuntimeException("Variable VCX_INSTITUTION_DID not set.");
		VCX_INSTITUTION_DID = E_VCX_INSTITUTION_DID;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_DID: " + VCX_INSTITUTION_DID);

		String E_VCX_INSTITUTION_VERKEY = System.getenv("VCX_INSTITUTION_VERKEY");
		if (E_VCX_INSTITUTION_VERKEY == null) throw new RuntimeException("Variable VCX_INSTITUTION_VERKEY not set.");
		VCX_INSTITUTION_VERKEY = E_VCX_INSTITUTION_VERKEY;
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_VERKEY: " + VCX_INSTITUTION_VERKEY);

		String E_VCX_CREDENTIAL_NAME = System.getenv("VCX_CREDENTIAL_NAME");
		if (E_VCX_CREDENTIAL_NAME == null) throw new RuntimeException("Variable VCX_CREDENTIAL_NAME not set.");
		VCX_CREDENTIAL_NAME = E_VCX_CREDENTIAL_NAME;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDENTIAL_NAME: " + VCX_CREDENTIAL_NAME);

		String E_VCX_CREDENTIAL_VALUE = System.getenv("VCX_CREDENTIAL_VALUE");
		if (E_VCX_CREDENTIAL_VALUE == null) throw new RuntimeException("Variable VCX_CREDENTIAL_VALUE not set.");
		VCX_CREDENTIAL_VALUE = E_VCX_CREDENTIAL_VALUE;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDENTIAL_VALUE: " + VCX_CREDENTIAL_VALUE);

		String E_VCX_DID_MAPPER = System.getenv("VCX_DID_MAPPER");
		if (E_VCX_DID_MAPPER == null) throw new RuntimeException("Variable VCX_DID_MAPPER not set.");
		VCX_DID_MAPPER = E_VCX_DID_MAPPER;
		if (logger.isInfoEnabled()) logger.info("VCX_DID_MAPPER: " + VCX_DID_MAPPER);

		String E_VCX_SCHEMA_ID = System.getenv("VCX_SCHEMA_ID");
		if (E_VCX_SCHEMA_ID != null && E_VCX_SCHEMA_ID.trim().isEmpty()) E_VCX_SCHEMA_ID = null;
		VCX_SCHEMA_ID = E_VCX_SCHEMA_ID;
		if (logger.isInfoEnabled()) logger.info("VCX_SCHEMA_ID: " + VCX_SCHEMA_ID);

		String E_VCX_CREDDEF_ID = System.getenv("VCX_CREDDEF_ID");
		if (E_VCX_CREDDEF_ID != null && E_VCX_CREDDEF_ID.trim().isEmpty()) E_VCX_CREDDEF_ID = null;
		VCX_CREDDEF_ID = E_VCX_CREDDEF_ID;
		if (logger.isInfoEnabled()) logger.info("VCX_CREDDEF_ID: " + VCX_CREDDEF_ID);
	}

	public static void init() {

	}
}
