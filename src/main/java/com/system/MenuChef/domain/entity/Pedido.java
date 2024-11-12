package com.system.MenuChef.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Pedido extends Base {

  @ManyToOne
  private Cliente client;

  @ManyToMany
  private List<Item> itens;

  private String deskPedido;

  private StatusPedido status;

  private LocalDateTime datePedido;

  private BigDecimal valueTotal;

}
