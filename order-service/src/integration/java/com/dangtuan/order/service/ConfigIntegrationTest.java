package com.dangtuan.order.service;

import com.dangtuan.order.dto.UserSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ConfigIntegrationTest {

  public static final String USER_SESSION = "userSessionTest";

  @Bean
  @Qualifier(USER_SESSION)
  @Profile("integration")
  public UserSession userSession() {
    final UserSession userSession = new UserSession();
    userSession.setTenantId("tenant_1");
    userSession.setUserName("tuan193@gmail.com");
    userSession.setToken("ACCESS_TOKEN");
    return userSession;
  }
}
