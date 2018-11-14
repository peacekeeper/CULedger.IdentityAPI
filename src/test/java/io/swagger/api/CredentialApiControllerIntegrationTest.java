package io.swagger.api;

import io.swagger.model.CULedgerCredentialBasic;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CredentialApiControllerIntegrationTest {

    @Autowired
    private CredentialApi api;

    @Test
    public void createCredentialTest() throws Exception {
        CULedgerCredentialBasic body = new CULedgerCredentialBasic();
        ResponseEntity<Void> responseEntity = api.createCredential(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getCredentialTest() throws Exception {
        String id = "id_example";
        ResponseEntity<String> responseEntity = api.getCredential(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
