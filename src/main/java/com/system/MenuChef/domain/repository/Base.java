package com.system.MenuChef.domain.repository;


import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import java.util.Optional;
import java.util.UUID;

public interface Base<T> {

  PageTO<T> findAll(PaginationTO paginationTO);

  T save(T entity);

  Optional<T> findById(UUID id);
}