package com.dangtuan.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

  private Long id;
  private DeliveryStatus status;
  private Long orderId;
  private Long tenantId;
}
