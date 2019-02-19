/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

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
@Api(value = "heartbeat", description = "the heartbeat API")
public interface HeartbeatApi {

    @ApiOperation(value = "gives developer information about the health of the system. [uses /agency hook for the Agency endpoint]", nickname = "getHeartbeat", notes = "", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK | NotOK", response = String.class) })
    @RequestMapping(value = "/heartbeat",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getHeartbeat();

}
