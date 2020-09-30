package com.dangtuan.resource.service.dto;

import com.dangtuan.resource.service.exception.UnauthorizedException;
import com.dangtuan.resource.service.util.TokenHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Log4j2
public class UserSession {

  public static final String UNAUTHORIZED_USER_ERR = "Token information invalid!";

  private String tenantId;
  private String userName;
  private String[] scope;
  private String customInfo;
  private String token;

  public void setUserSession(final String accessToken) throws IOException {
    final UserDetails userDetails = TokenHelper.decode(accessToken);
    if (!ObjectUtils.allNotNull(userDetails.getUserName(), userDetails.getTenantId())) {
      throw new UnauthorizedException(UNAUTHORIZED_USER_ERR);
    }
    this.setTenantId(userDetails.getTenantId());
    this.setUserName(userDetails.getUserName());
    this.setToken(accessToken);
    this.setCustomInfo(userDetails.getCustomInfo());
    this.setScope(userDetails.getScope());
  }
}