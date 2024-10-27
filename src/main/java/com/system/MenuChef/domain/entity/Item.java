package com.system.MenuChef.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens")
@EqualsAndHashCode(callSuper = true)
public class Item extends Base {

  private String itemName;

  private BigDecimal itemPrice;

  private String itemDesc;

  @ManyToOne
  private Categoria category;

  private Boolean available;

  @JsonIgnore
  @Temporal(TemporalType.DATE)
  @Column(name = "deleted_at")
  private Date deletedAt;

  @JsonIgnore
  @Temporal(TemporalType.DATE)
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  @JsonIgnore
  @Temporal(TemporalType.DATE)
  @Column(name = "created_at", nullable = false)
  private Date createdAT;

}
