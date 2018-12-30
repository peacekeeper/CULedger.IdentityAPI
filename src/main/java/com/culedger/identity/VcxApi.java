package com.culedger.identity;
import com.culedger.identity.didmapper.DbMemberDidMapper;
import com.culedger.identity.didmapper.MemberDidMapper;

import net.minidev.json.parser.JSONParser;

public class VcxApi {

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
	static JSONParser jsonParser;

	static {

		try {

			VcxConfiguration.init();
			VcxInit.init();

			memberDidMapper = new DbMemberDidMapper();
			jsonParser = new JSONParser(JSONParser.MODE_STRICTEST);
		} catch (Exception ex) {

			ex.printStackTrace(System.err);
			System.exit(-1);
		}
	}
}
