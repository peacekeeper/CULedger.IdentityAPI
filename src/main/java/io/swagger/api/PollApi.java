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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-02T19:50:39.116Z[GMT]")
@Api(value = "poll", description = "the poll API")
public interface PollApi {

    @ApiOperation(value = "checks job status, providing result (JSON)", nickname = "poll", notes = "TODO", response = String.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "JSON of Job State (and Results)", response = String.class),
        @ApiResponse(code = 204, message = "no result yet"),
        @ApiResponse(code = 404, message = "job not found") })
    @RequestMapping(value = "/poll/{jobId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> poll(@ApiParam(value = "The jobID that was returned from a polling-based call  (Onboard or Authenticate). ",required=true) @PathVariable("jobId") String jobId);

}