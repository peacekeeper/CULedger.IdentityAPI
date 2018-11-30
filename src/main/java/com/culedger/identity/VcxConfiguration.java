package com.culedger.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VcxConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(Vcx.class);

	public static final String VCX_INSTITUTION_NAME;
	public static final String VCX_CREDENTIAL_NAME;
	
	static {

		VCX_INSTITUTION_NAME = System.getenv("VCX_INSTITUTION_NAME");
		if (VCX_INSTITUTION_NAME == null) throw new RuntimeException("Variable VCX_INSTITUTION_NAME not set.");
		if (logger.isInfoEnabled()) logger.info("VCX_INSTITUTION_NAME: " + VCX_INSTITUTION_NAME);

		VCX_CREDENTIAL_NAME = System.getenv("VCX_CREDENTIAL_NAME");
		if (VCX_CREDENTIAL_NAME == null) throw new RuntimeException("Variable VCX_CREDENTIAL_NAME not set.");
		if (logger.isInfoEnabled()) logger.info("VCX_CREDENTIAL_NAME: " + VCX_CREDENTIAL_NAME);
	}
}
