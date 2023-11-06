package com.dangtuan.order.config;

import com.dangtuan.order.dto.MatcherMappingDto;
import com.dangtuan.order.service.MatcherService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

//TODO spring security not reload even config reload
@Component(value = "webSecurity")
public class webSecurity {

//  @Autowired
//  private MatcherService matcherService;
//
//  private List<MatcherMappingDto> path;
//
//  public List<MatcherMappingDto> getPath() {
//    path = matcherService.getMatchers();
//    return path;
//  }
//
//  public void setPath(List<MatcherMappingDto> path) {
//    this.path = path;
//  }

  public boolean check(Authentication authentication, HttpServletRequest request) {
    authentication.getAuthorities();
    authentication.getCredentials();
    return true;
  }
}