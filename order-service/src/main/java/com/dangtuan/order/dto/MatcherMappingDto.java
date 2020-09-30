package com.dangtuan.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatcherMappingDto {

  private String endpoint;
  private String authority;
  private String methodType;
}
