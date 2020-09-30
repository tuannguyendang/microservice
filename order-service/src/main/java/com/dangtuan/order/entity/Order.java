package com.dangtuan.order.entity;

import com.dangtuan.order.entity.base.BaseEntity;
import com.dangtuan.order.util.constants.EntityConstants;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = EntityConstants.TBL_ORDER)
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {

  @Column(name = EntityConstants.AMOUNT)
  private Long amount;

  @Column(name = EntityConstants.PRICE)
  private BigDecimal price;
}
