package com.culedger.identity.didmapper;

public interface MemberDidMapper {

	public void add(String memberId, Integer connectionHandle) throws Exception;
	public Integer getAsConnectionHandle(String memberId) throws Exception;
}
