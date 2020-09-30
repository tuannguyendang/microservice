package com.dangtuan.order.entity.base;

import com.dangtuan.order.util.constants.EntityConstants;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = EntityConstants.ID, unique = true, nullable = false)
  private Long id;

  @Column(name = EntityConstants.DELETED, nullable = false)
  private Boolean deleted;

  @Column(name = EntityConstants.TENANT_ID, nullable = false)
  private String tenantId;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = EntityConstants.CREATED_DATE, nullable = false, updatable = false)
  @CreatedDate
  private Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = EntityConstants.UPDATED_DATE)
  @LastModifiedDate
  private Date updatedDate;

  @Column(name = EntityConstants.CREATED_BY)
  @CreatedBy
  private String createdBy;

  @Column(name = EntityConstants.UPDATED_BY)
  @LastModifiedBy
  private String updatedBy;

  @PrePersist
  public void prePersist() {
    this.deleted = Boolean.FALSE;
  }
}
