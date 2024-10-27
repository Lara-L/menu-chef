package com.system.MenuChef.domain.repository;

import com.system.MenuChef.domain.entity.Cliente;
import java.util.Optional;

public interface ClienteRepository extends Base<Cliente>{
  Optional<Cliente> findClienteByDesk(String desk);
}
