package io.swagger.api;

import io.swagger.model.CULedgerMember;
import io.swagger.model.CULedgerMessage;
import io.swagger.model.CULedgerMessageResponse;
import io.swagger.model.CULedgerOnboardingData;

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
public class MemberApiControllerIntegrationTest {

    @Autowired
    private MemberApi api;

    @Test
    public void createMemberTest() throws Exception {
        CULedgerMember body = new CULedgerMember();
        ResponseEntity<Void> responseEntity = api.createMember(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getMemberTest() throws Exception {
        String memberId = "memberId_example";
        ResponseEntity<String> responseEntity = api.getMember(memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void memberAuthenticateTest() throws Exception {
        String memberId = "memberId_example";
        ResponseEntity<CULedgerMember> responseEntity = api.memberAuthenticate(memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void memberConnectTest() throws Exception {
        String memberId = "memberId_example";
        ResponseEntity<String> responseEntity = api.memberConnect(memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void memberOnboardTest() throws Exception {
        CULedgerOnboardingData body = new CULedgerOnboardingData();
        String memberId = "memberId_example";
        ResponseEntity<CULedgerOnboardingData> responseEntity = api.memberOnboard(body, memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void memberSendCredentialTest() throws Exception {
        String memberId = "memberId_example";
        ResponseEntity<String> responseEntity = api.memberSendCredential(memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void memberSendMessageTest() throws Exception {
        CULedgerMessage body = new CULedgerMessage();
        String memberId = "memberId_example";
        ResponseEntity<CULedgerMessageResponse> responseEntity = api.memberSendMessage(body, memberId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
