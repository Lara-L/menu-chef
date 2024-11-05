package com.system.MenuChef.domain.service;

import com.system.MenuChef.domain.TO.CategoriaTO;
import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import com.system.MenuChef.domain.entity.Categoria;
import com.system.MenuChef.domain.exceptions.DomainException;
import com.system.MenuChef.domain.exceptions.ErrorCode;
import com.system.MenuChef.domain.repository.CategoriaRepository;
import java.util.Optional;
import java.util.UUID;

public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public PageTO<Categoria> getAll(PaginationTO paginationTO) {
    return categoriaRepository.findAll(paginationTO);
  }

  public Categoria findById(UUID id) throws DomainException {
    Optional<Categoria> category = categoriaRepository.findById(id);
    if (category.isEmpty()) {
      throw new DomainException(ErrorCode.CATEGORY_NOT_FOUND);
    }
    return category.get();
  }
  public Optional<Categoria> findCategoryByName(String name) {
    return categoriaRepository.findCategoryByName(upperAndSnakeCase(name));
  }

  public Categoria createCategory(CategoriaTO categoriaTO) throws DomainException {
    Optional<Categoria> categoryDB = categoriaRepository.findCategoryByName(upperAndSnakeCase(categoriaTO.getCategoryName()));
    if (categoryDB.isPresent()) {
      throw new DomainException(ErrorCode.CATEGORY_EXISTENT);
    }
    Categoria categoria = Categoria.builder()
        .categoryName(upperAndSnakeCase(categoriaTO.getCategoryName()))
        .build();

    return categoriaRepository.save(categoria);
  }

  public Categoria updateCategory(CategoriaTO categoriaTO, UUID id) throws DomainException {
    Optional<Categoria> category = categoriaRepository.findById(id);
    if (!upperAndSnakeCase(categoriaTO.getCategoryName()).equals(category.get().getCategoryName())) {
      Optional<Categoria> categoryDB =categoriaRepository.findCategoryByName(upperAndSnakeCase(categoriaTO.getCategoryName()));

      if (categoryDB.isPresent()) {
        throw new DomainException(ErrorCode.CATEGORY_EXISTENT);
      }
      category.get().setCategoryName(upperAndSnakeCase(categoriaTO.getCategoryName()));
    }

    return categoriaRepository.save(category.get());
  }

  public void deleteCategory(UUID id) throws DomainException {
    Optional<Categoria> category = categoriaRepository.findById(id);
    categoriaRepository.delete(category.get());
  }

  private String upperAndSnakeCase(String categoryToString) {
    return categoryToString.toUpperCase().replace(" ","_");
  }
}