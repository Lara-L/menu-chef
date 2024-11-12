package com.system.MenuChef.domain.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Base {

  private String name;

  private String phone;

  private String email;

  private String desk;

  @Temporal(TemporalType.DATE)
  @Column(name = "deleted_at")
  private Date deletedAt;

  @Temporal(TemporalType.DATE)
  @Column(name = "update_at")
  private Date updatedAt;

  @Temporal(TemporalType.DATE)
  @Column(name = "created_at")
  private Date createdAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }

}
