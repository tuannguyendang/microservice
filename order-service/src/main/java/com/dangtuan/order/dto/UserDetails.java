package com.dangtuan.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

  @JsonProperty("client_id")
  private String tenantId;
  @JsonProperty("user_name")
  private String userName;
  private String[] scope;
  private String customInfo;
  private String token;
}
