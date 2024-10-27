package com.system.MenuChef.domain.TO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteTO {

  @NotNull
  @NotEmpty
  private String name;

  private String phone;

  private String email;

  @NotNull
  @NotEmpty
  private String desk;
}
