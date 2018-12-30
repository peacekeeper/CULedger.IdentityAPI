package com.culedger.identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.culedger.identity.didmapper.DbMemberDidMapper;
import com.culedger.identity.didmapper.MemberDidMapper;
import com.culedger.identity.didmapper.MemoryMemberDidMapper;

import net.minidev.json.parser.JSONParser;

public class Vcx {

	private static final Logger logger = LoggerFactory.getLogger(Vcx.class);
	private static final JSONParser jsonParser = new JSONParser(JSONParser.MODE_STRICTEST);

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

	static MemberDidMapper memberDidMapper;

	static {

		VcxConfiguration.init();
		VcxInit.init();

		try {

			// create member->DID mapper

			if (VcxConfiguration.VCX_DID_MAPPER.equals("memory")) {

				memberDidMapper = new MemoryMemberDidMapper();
			} else if (VcxConfiguration.VCX_DID_MAPPER.equals("db")) {

				memberDidMapper = new DbMemberDidMapper();
			} else {

				throw new IllegalArgumentException("Unknown DID->mapper type.");
			}
		} catch (Exception ex) {

			if (logger.isErrorEnabled()) logger.error(ex.getMessage(), ex);
			ex.printStackTrace(System.err);
			System.exit(-1);
		}
	}
}
