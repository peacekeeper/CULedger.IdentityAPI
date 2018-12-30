package io.swagger.api;

import io.swagger.model.CULedgerKeyPair;

import com.culedger.identity.Vcx;
import com.culedger.identity.VcxApiConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-30T11:45:41.226Z[GMT]")

@Controller
public class ConfigApiController implements ConfigApi {

    private static final Logger log = LoggerFactory.getLogger(ConfigApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ConfigApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> getConfigValue(@ApiParam(value = "valueName in valueName/value keypair",required=true) @PathVariable("valueName") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<CULedgerKeyPair>> listConfigSettings() {
        return VcxApiConfig.listConfigSettings();
    }

    public ResponseEntity<String> setConfigValue(@ApiParam(value = "" ,required=true )  @Valid @RequestBody String body, @ApiParam(value = "valueName in valueName/value keypair",required=true) @PathVariable("valueName") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

}
