package com.dangtuan.resource.service.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class OrderDto {

  private Long id;

  private Long amount;

  private BigDecimal price;

  private Long tenantId;

  private Boolean deleted;

  private Date createdDate;

  private Date updatedDate;

  private Long createdBy;

  private Long updatedBY;

}
