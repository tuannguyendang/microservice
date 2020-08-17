package com.dangtuan.auth.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * Class handle customize token append information
 */
@Component
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

  private static final String CUSTOM_INFO = "customInfo";
  private static final String AUTHORITIES = "authorities";
  private static final String USER_NAME = "userName";

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    final Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put(CUSTOM_INFO, "tuan nguyen auth server");
    additionalInfo.put(AUTHORITIES, userDetails.getAuthorities());
    additionalInfo.put(USER_NAME, userDetails.getUsername());

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

    return super.enhance(accessToken, authentication);
  }
}
