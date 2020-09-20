package com.dangtuan.common.dto;

import com.dangtuan.common.constants.CommonConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

  @JsonProperty(CommonConstants.CLIENT_ID)
  private String tenantId;
  @JsonProperty(CommonConstants.USER_NAME)
  private String userName;
  private String[] scope;
  private String customInfo;
  private String token;
}
