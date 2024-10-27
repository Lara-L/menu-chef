package com.system.MenuChef.domain.repository;

import com.system.MenuChef.domain.entity.Item;
import java.util.Optional;

public interface ItemRepository extends Base<Item>{
  Optional<Item> findByName(String itemName);
}
