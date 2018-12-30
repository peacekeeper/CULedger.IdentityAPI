package com.culedger.identity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class VcxApiHeartbeat extends VcxApi {

	public static ResponseEntity<String> getHeartbeat() {

		return new ResponseEntity<String>(HttpStatus.OK) ;
	}
}
