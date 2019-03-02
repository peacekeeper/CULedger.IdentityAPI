/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.CULedgerMember;
import io.swagger.model.CULedgerOnboardingData;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-02T19:50:39.116Z[GMT]")
@Api(value = "member", description = "the member API")
public interface MemberApi {

    @ApiOperation(value = "adds a Member item", nickname = "createMember", notes = "Adds a Member to the system", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created") })
    @RequestMapping(value = "/member",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createMember(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CULedgerMember body);


    @ApiOperation(value = "Returns Member with memberId.", nickname = "getMember", notes = "...", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "JSON Verifiable Credential (Sovrin)", response = String.class) })
    @RequestMapping(value = "/member/{memberId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getMember(@ApiParam(value = "blah blah",required=true) @PathVariable("memberId") String memberId);


    @ApiOperation(value = "Authenticates that Member has control of valid Verifiable Credential", nickname = "memberAuthenticate", notes = "By passing in the appropriate options, you can search forMember by MemberID, DID, TBD. Parameters here are BOGUS.", response = CULedgerMember.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = CULedgerMember.class),
        @ApiResponse(code = 202, message = "job is in progress") })
    @RequestMapping(value = "/member/{memberId}/authenticate",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<CULedgerMember> memberAuthenticate(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId,@ApiParam(value = "" ) @RequestHeader(value="Prefer", required=false) String prefer);


    @ApiOperation(value = "Makes initial connection to the member", nickname = "memberConnect", notes = "Begins connection", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = String.class),
        @ApiResponse(code = 202, message = "job is in progress") })
    @RequestMapping(value = "/member/{memberId}/connect",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<String> memberConnect(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId,@ApiParam(value = "" ) @RequestHeader(value="Prefer", required=false) String prefer);


    @ApiOperation(value = "Connects to Member and sends Credential to them.", nickname = "memberOnboard", notes = "At low level connects to Member (via private-DID-pair) and sends their Credential.", response = CULedgerOnboardingData.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = CULedgerOnboardingData.class),
        @ApiResponse(code = 202, message = "job is in progress"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/member/{memberId}/onboard",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<CULedgerOnboardingData> memberOnboard(@ApiParam(value = "" ,required=true )  @Valid @RequestBody CULedgerOnboardingData body,@ApiParam(value = "Member (identitified by memberID) that we are onboarding.",required=true) @PathVariable("memberId") String memberId,@ApiParam(value = "" ) @RequestHeader(value="Prefer", required=false) String prefer);


    @ApiOperation(value = "Makes initial connection to the member", nickname = "memberSendCredential", notes = "Sends credential to Member", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = String.class),
        @ApiResponse(code = 202, message = "job is in progress") })
    @RequestMapping(value = "/member/{memberId}/sendCredential",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<String> memberSendCredential(@ApiParam(value = "pass an optional search string for looking up Members",required=true) @PathVariable("memberId") String memberId,@ApiParam(value = "" ) @RequestHeader(value="Prefer", required=false) String prefer);

}
