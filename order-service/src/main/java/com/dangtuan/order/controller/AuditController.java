package com.dangtuan.order.controller;

import com.dangtuan.order.util.constants.ApiConstants;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = ApiConstants.AUDIT_API, produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
@Api(value = ApiConstants.AUDIT_API)
public class AuditController {
    @RequestMapping(value = ApiConstants.AUDIT_REPORT, method = RequestMethod.GET)
    public ResponseEntity<String> auditAdmin() {
        return new ResponseEntity<>("Start Audit", HttpStatus.OK);
    }

    @RequestMapping(value = ApiConstants.AUDIT_MANGER, method = RequestMethod.GET)
    public ResponseEntity<String> auditManager() {
        return new ResponseEntity<>("Start Manager", HttpStatus.OK);
    }
}
