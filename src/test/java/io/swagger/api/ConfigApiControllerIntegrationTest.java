package io.swagger.api;

import io.swagger.model.CULedgerKeyPair;

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
public class ConfigApiControllerIntegrationTest {

    @Autowired
    private ConfigApi api;

    @Test
    public void getConfigValueTest() throws Exception {
        String valueName = "valueName_example";
        ResponseEntity<String> responseEntity = api.getConfigValue(valueName);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void listConfigSettingsTest() throws Exception {
        ResponseEntity<List<CULedgerKeyPair>> responseEntity = api.listConfigSettings();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void setConfigValueTest() throws Exception {
        String body = "body_example";
        String valueName = "valueName_example";
        ResponseEntity<String> responseEntity = api.setConfigValue(body, valueName);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
