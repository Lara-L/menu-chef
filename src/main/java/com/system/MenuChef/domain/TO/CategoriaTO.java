package com.system.MenuChef.domain.TO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaTO {

  @NotNull
  @NotEmpty
  @Size(min = 2, message = "{validation.name.size.too_short}")
  @Size(max = 40, message = "{validation.name.size.too_long}")
  private String name;
}
