package com.culedger.identity;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.connection.ConnectionApi;

public class MemberDidMapper {

	private static final Logger logger = LoggerFactory.getLogger(MemberDidMapper.class);
	private static final Map<String, String> connectionDataMap = new HashMap<String, String> ();
	private static final Map<String, Integer> connectionHandleMap = new HashMap<String, Integer> ();

	public static void add(String memberId, Integer connectionHandle) throws VcxException, InterruptedException, ExecutionException {

		String connectionData = ConnectionApi.connectionSerialize(connectionHandle.intValue()).get();

		connectionDataMap.put(memberId, connectionData);
		connectionHandleMap.put(memberId, connectionHandle);
		if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " stored connection handle " + connectionHandle + " and connection data " + connectionData);
	}

	public static String getAsConnectionData(String memberId) {

		return connectionDataMap.get(memberId);
	}

	public static Integer getAsConnectionHandle(String memberId) throws VcxException, InterruptedException, ExecutionException {

/*		String connectionData = connectionDataMap.get(memberId);
		if (connectionData == null) return null;

		Integer connectionHandle = ConnectionApi.connectionDeserialize(connectionData).get();
		if (logger.isInfoEnabled()) logger.info("Deserialized connection data " + connectionData + " for member ID " + memberId + " to " + connectionHandle);*/
		
		Integer connectionHandle = connectionHandleMap.get(memberId);
		if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " found connection handle " + connectionHandle);

		return connectionHandle;
	}
}
