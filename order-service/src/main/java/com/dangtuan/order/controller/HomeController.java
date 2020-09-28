package com.dangtuan.order.controller;

import com.dangtuan.order.util.constants.ApiConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This controller class handles only one request mapping
 */
@Controller
public class HomeController {

  @RequestMapping(value = ApiConstants.SEPARATOR, method = RequestMethod.GET)
  public String home() {
    return ApiConstants.SWAGGER_REDIRECT;
  }
}
