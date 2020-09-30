package com.dangtuan.auth.entity;

import com.dangtuan.auth.util.constants.EntityConstants;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = EntityConstants.AUTHORITIES_TABLE)
@Data
public class Authorities implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = EntityConstants.USER_NAME)
  private String userName;

  @Column
  private String authority;

}
