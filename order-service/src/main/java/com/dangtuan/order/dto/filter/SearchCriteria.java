package com.dangtuan.order.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {

  private String key;
  private SearchOperation operation;
  private Object value;
}