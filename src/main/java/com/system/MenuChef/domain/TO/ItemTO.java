package com.system.MenuChef.domain.TO;

import com.system.MenuChef.domain.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class ItemTO {

  @NotNull
  @NotBlank
  private String itemName;

  @NotNull
  @NotBlank
  private BigDecimal itemPrice;

  private String itemDesc;

  private Categoria category;

  @NotNull
  @NotBlank
  private Boolean available;
}
