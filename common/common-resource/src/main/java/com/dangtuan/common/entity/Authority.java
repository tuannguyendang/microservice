package com.dangtuan.common.entity;

import com.dangtuan.common.util.constants.EntityConstants;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = EntityConstants.AUTHORITY)
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 20)
  private String name;

  @OneToMany(mappedBy = EntityConstants.AUTHORITY)
  private List<Matcher> matchers;
}
