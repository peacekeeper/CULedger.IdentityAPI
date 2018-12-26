package com.culedger.identity.didmapper;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evernym.sdk.vcx.VcxException;
import com.evernym.sdk.vcx.connection.ConnectionApi;

public class DbMemberDidMapper implements MemberDidMapper {

	private static final Logger logger = LoggerFactory.getLogger(DbMemberDidMapper.class);
	private final RocksDB db;

	public DbMemberDidMapper() {

		RocksDB.loadLibrary();

		try (final Options options = new Options()) {

			options.setCreateIfMissing(true);
			db = RocksDB.open(options, ".");
		} catch (RocksDBException ex) {

			if (logger.isErrorEnabled()) logger.error("Cannot initialize DB: " + ex.getMessage(), ex);
			throw new RuntimeException(ex.getMessage(), ex);
		}

		Runtime.getRuntime().addShutdownHook(new Thread() { 

			public void run() { 

				if (db != null) db.close();
			} 
		}); 	
	}

	@Override
	public void add(String memberId, Integer connectionHandle) throws InterruptedException, ExecutionException, VcxException, RocksDBException {

		String connectionData = ConnectionApi.connectionSerialize(connectionHandle.intValue()).get();

		db.put(memberId.getBytes(StandardCharsets.UTF_8), connectionData.getBytes(StandardCharsets.UTF_8));
		if (logger.isInfoEnabled()) logger.info("For member ID " + memberId + " stored connection handle " + connectionHandle + " and connection data " + connectionData);
	}

	@Override
	public Integer getAsConnectionHandle(String memberId) throws InterruptedException, ExecutionException, VcxException, RocksDBException {

		byte[] value = db.get(memberId.getBytes(StandardCharsets.UTF_8));
		String connectionData = value == null ? null : new String(value, StandardCharsets.UTF_8);
		if (connectionData == null) return null;

		Integer connectionHandle = ConnectionApi.connectionDeserialize(connectionData).get();
		if (logger.isInfoEnabled()) logger.info("Deserialized connection data " + connectionData + " for member ID " + memberId + " to " + connectionHandle);

		return connectionHandle;
	}
}
