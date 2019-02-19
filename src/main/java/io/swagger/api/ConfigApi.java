/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.CULedgerKeyPair;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-19T12:02:09.781Z[GMT]")
@Api(value = "config", description = "the config API")
public interface ConfigApi {

    @ApiOperation(value = "get Value of configuration setting with name valueName.", nickname = "getConfigValue", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/config/{valueName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getConfigValue(@ApiParam(value = "valueName in valueName/value keypair",required=true) @PathVariable("valueName") String valueName);


    @ApiOperation(value = "provides the basic configuration information about the system.", nickname = "listConfigSettings", notes = "", response = CULedgerKeyPair.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = CULedgerKeyPair.class, responseContainer = "List") })
    @RequestMapping(value = "/config",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<CULedgerKeyPair>> listConfigSettings();


    @ApiOperation(value = "get Value of configuration setting with name valueName.", nickname = "setConfigValue", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @RequestMapping(value = "/config/{valueName}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<String> setConfigValue(@ApiParam(value = "" ,required=true )  @Valid @RequestBody String body,@ApiParam(value = "valueName in valueName/value keypair",required=true) @PathVariable("valueName") String valueName);

}
