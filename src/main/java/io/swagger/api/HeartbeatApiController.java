package io.swagger.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.culedger.identity.VcxApiHeartbeat;
import com.fasterxml.jackson.databind.ObjectMapper;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-30T11:45:41.226Z[GMT]")

@Controller
public class HeartbeatApiController implements HeartbeatApi {

    private static final Logger log = LoggerFactory.getLogger(HeartbeatApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public HeartbeatApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<String> getHeartbeat() {
        return VcxApiHeartbeat.getHeartbeat();
    }

}
