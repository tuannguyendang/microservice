package com.dangtuan.resource.service.controller;

import com.dangtuan.resource.service.util.constants.ApiConstants;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create your APIs
 */
@Controller
@RequestMapping(ApiConstants.USER_API)
public class UserController {

  @GetMapping(ApiConstants.USER_PROFILE_API)
  public @ResponseBody
  String getOauth2Principal(OAuth2Authentication auth) {
    return "Get access granted for " + auth.getPrincipal();
  }

  @PostMapping(ApiConstants.USER_PROFILE_API)
  public @ResponseBody
  String postOauth2Principal(OAuth2Authentication auth) {
    return "Post access granted for " + auth.getName();
  }
}
