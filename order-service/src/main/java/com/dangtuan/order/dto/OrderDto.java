package com.dangtuan.order.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

  private Long id;

  private Long amount;

  private BigDecimal price;

  private String tenantId;

  private Boolean deleted;

  private Date createdDate;

  private Date updatedDate;

  private String createdBy;

  private String updatedBY;

}
