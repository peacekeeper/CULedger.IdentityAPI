/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.CULedgerCredentialBasic;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-19T08:00:41.414Z[GMT]")

@Api(value = "credential", description = "the credential API")
public interface CredentialApi {

    @ApiOperation(value = "creates a credential", nickname = "createCredential", notes = "Adds an item to the system", tags={ "internal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/credential",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> createCredential(@ApiParam(value = ""  )  @Valid @RequestBody CULedgerCredentialBasic body);


    @ApiOperation(value = "returns Credential that matches input id.", nickname = "getCredential", notes = "...", response = String.class, tags={ "internal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "JSON Verifiable Credential (Sovrin)", response = String.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/credential/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getCredential(@ApiParam(value = "id of the credential we're looking at.",required=true) @PathVariable("id") String id);

}