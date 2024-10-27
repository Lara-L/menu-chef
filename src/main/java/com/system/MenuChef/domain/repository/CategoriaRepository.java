package com.system.MenuChef.domain.repository;

import com.system.MenuChef.domain.entity.Categoria;
import java.util.Optional;

public interface CategoriaRepository extends Base<Categoria>{
  Optional<Categoria> findCategoryByName(String categoryName);

  void delete(Categoria category);
}
