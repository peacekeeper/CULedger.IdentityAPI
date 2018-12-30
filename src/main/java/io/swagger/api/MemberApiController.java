package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.culedger.identity.VcxApiMember;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.CULedgerMember;
import io.swagger.model.CULedgerMessage;
import io.swagger.model.CULedgerMessageResponse;
import io.swagger.model.CULedgerOnboardingData;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-30T11:45:41.226Z[GMT]")

@Controller
public class MemberApiController implements MemberApi {

    private static final Logger log = LoggerFactory.getLogger(MemberApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MemberApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createMember(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CULedgerMember body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> getMember(@ApiParam(value = "blah blah",required=true) @PathVariable("memberId") String memberId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CULedgerMember> memberAuthenticate(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId) {
        return VcxApiMember.memberAuthenticate(memberId);
    }

    public ResponseEntity<String> memberConnect(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CULedgerOnboardingData> memberOnboard(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CULedgerOnboardingData body, @ApiParam(value = "Member (identitified by memberID) that we are onboarding.",required=true) @PathVariable("memberId") String memberId) {
        return VcxApiMember.memberOnBoard(body, memberId);
    }

    public ResponseEntity<String> memberSendCredential(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId) {
        return VcxApiMember.memberSendCredential(memberId);
    }

    public ResponseEntity<CULedgerMessageResponse> memberSendMessage(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CULedgerMessage body, @ApiParam(value = "Member (identitified by memberID) that we are onboarding.",required=true) @PathVariable("memberId") String memberId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<CULedgerMessageResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
