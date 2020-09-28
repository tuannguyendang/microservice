package com.dangtuan.resource.service.controller;

import com.dangtuan.resource.service.util.constants.ApiConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(ApiConstants.PUBLIC_API)
public class PublicController {

  @RequestMapping(ApiConstants.SEPARATOR)
  public @ResponseBody
  String sayHi() {
    return "Hey Good Day!";
  }

}