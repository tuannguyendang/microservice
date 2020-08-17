package com.dangtuan.auth.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "username")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column
  private String firstName;
  @Column
  private String lastName;
  @Column
  private String email;
  @Column
  private Boolean enabled;

  @Column
  private Boolean accountExpired;

  @Column
  private Boolean accountLocked;

  @Column
  private Boolean credentialsExpired;

  @OneToMany(fetch = FetchType.EAGER)
  private Set<Authorities> userAuthorities = new HashSet<Authorities>();
}
