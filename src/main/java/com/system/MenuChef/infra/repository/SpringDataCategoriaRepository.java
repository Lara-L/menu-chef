package com.system.MenuChef.infra.repository;

import com.system.MenuChef.domain.entity.Categoria;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoriaRepository extends JpaRepository<Categoria, UUID> {
    Optional<Categoria> findCategoriaByName(String name);
}
