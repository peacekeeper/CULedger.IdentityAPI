package com.culedger.identity.didmapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evernym.sdk.vcx.VcxException;

public class MemoryMemberDidMapper implements MemberDidMapper {

	private static final Logger logger = LoggerFactory.getLogger(MemoryMemberDidMapper.class);

	private final Map<String, Integer> connectionHandleMap = new HashMap<String, Integer> ();

	@Override
	public void add(String memberId, Integer connectionHandle) throws VcxException, InterruptedException, ExecutionException {

		this.connectionHandleMap.put(memberId, connectionHandle);
		if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " stored connection handle " + connectionHandle);
	}

	@Override
	public Integer getAsConnectionHandle(String memberId) throws VcxException, InterruptedException, ExecutionException {

		Integer connectionHandle = this.connectionHandleMap.get(memberId);
		if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " found connection handle " + connectionHandle);

		return connectionHandle;
	}
}
