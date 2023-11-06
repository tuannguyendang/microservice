package com.dangtuan.order.entity;

import com.dangtuan.order.entity.base.BaseEntity;
import com.dangtuan.order.util.constants.EntityConstants;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = EntityConstants.TBL_PRODUCT)
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {
  private String name;
  private int amount;
}
