package com.dangtuan.service.controller;

import com.dangtuan.service.util.constants.ApiConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ApiConstants.DELIVERY_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  @GetMapping
  public ResponseEntity<String> home() {
    return new ResponseEntity<>("Delivery Hi", HttpStatus.OK);
  }
}
