package com.tinnova.vehicleregister.domain.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
  private static final long serialVersionUID = 1L;

  @CreationTimestamp
  @Column(name = "created_date")
  private ZonedDateTime createdDate;

  @UpdateTimestamp
  @Column(name = "updated_date")
  private ZonedDateTime updatedDate;

}
