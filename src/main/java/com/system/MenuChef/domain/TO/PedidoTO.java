package com.system.MenuChef.domain.TO;

import com.system.MenuChef.domain.entity.Cliente;
import com.system.MenuChef.domain.entity.Item;
import com.system.MenuChef.domain.entity.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoTO {

  @NotBlank
  @NotNull
  private UUID clientId;

  @NotBlank
  @NotNull
  private List<UUID> itensIds;

  @NotBlank
  @NotNull
  private String deskPedido;

  @NotBlank
  @NotNull
  private StatusPedido status;

  @NotBlank
  @NotNull
  private LocalDateTime datePedido;

  @NotBlank
  @NotNull
  private BigDecimal valueTotal;
}
