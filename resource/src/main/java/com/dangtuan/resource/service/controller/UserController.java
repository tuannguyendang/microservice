package com.dangtuan.resource.service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("user")
public class UserController {

  @GetMapping("/profile")
  public @ResponseBody
  String getOauth2Principal(OAuth2Authentication auth) {
    return "Get access granted for ";
//    + auth.getPrincipal();
  }

  @PostMapping("/profile")
  public @ResponseBody
  String postOauth2Principal(@AuthenticationPrincipal UserDetails auth) {
    return "Post access granted for " + auth.getUsername();
  }
}
