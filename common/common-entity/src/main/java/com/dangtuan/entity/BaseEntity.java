package com.dangtuan.entity;

import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

  private Long id;
}
