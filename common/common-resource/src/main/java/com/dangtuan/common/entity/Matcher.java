package com.dangtuan.common.entity;

import com.dangtuan.common.util.constants.EntityConstants;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = EntityConstants.MATCHER)
public class Matcher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = EntityConstants.AUTH_ID)
  private Authority authority;

  private String endpoint;

  private String methodType;
}
