package com.dangtuan.resource.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatcherMappingDto {

  private String endpoint;
  private String authority;
  private String methodType;
}
