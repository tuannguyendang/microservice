package com.dangtuan.entity.order;

import com.dangtuan.entity.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "order")
@EqualsAndHashCode(callSuper=false)
public class Order extends BaseEntity {

  private Long amount;

}
