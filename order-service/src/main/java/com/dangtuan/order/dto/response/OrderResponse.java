package com.dangtuan.order.dto.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderResponse {

  private Long amount;
  private BigDecimal price;
}
