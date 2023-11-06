package com.dangtuan.auditor.controller;

import com.dangtuan.auditor.service.AuditorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auditor")
@Log4j2
public class AuditorController {

  @Autowired
  private AuditorService auditorService;

  @GetMapping()
  public ResponseEntity<Void> getMessage() {
    auditorService.process();
    final String message = auditorService.processHi();
    log.info("Message {}", message);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
