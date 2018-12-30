package com.culedger.identity;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.model.CULedgerKeyPair;

public class VcxApiConfig extends VcxApi {

    public static ResponseEntity<List<CULedgerKeyPair>> listConfigSettings() {

    	List<CULedgerKeyPair> configSettings = VcxConfiguration.makeCULedgerKeyPairs();

    	return new ResponseEntity<List<CULedgerKeyPair>>(configSettings, HttpStatus.OK);
    }
}
