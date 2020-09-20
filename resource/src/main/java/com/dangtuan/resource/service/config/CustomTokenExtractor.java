package com.dangtuan.resource.service.config;

import com.dangtuan.resource.service.dto.UserSession;
import java.util.Enumeration;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Log4j2
@Component
public class CustomTokenExtractor implements TokenExtractor {

  @Bean
  @RequestScope
  public UserSession userSession() {
    return new UserSession();
  }

  @Autowired
  private Provider<UserSession> userInfoProvider;

  private final String TOKEN_INFORMATION_INVALID = "Token input empty";
  public static final String AUTHORIZATION = "Authorization";

  @Override
  public Authentication extract(HttpServletRequest request) {
    Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
    while (headers.hasMoreElements()) {
      String value = headers.nextElement();
      if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
        String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
            value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
        int commaIndex = authHeaderValue.indexOf(',');
        if (commaIndex > 0) {
          authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }
        this.getUserDetails(authHeaderValue);
        return new PreAuthenticatedAuthenticationToken(authHeaderValue, "");
      }
    }
    return null;
  }

  public void getUserDetails(final String token) {
    try {
      final UserSession user = userInfoProvider.get();
      user.setUserSession(token);
    } catch (Exception ex) {
      return;
    }
  }
}
