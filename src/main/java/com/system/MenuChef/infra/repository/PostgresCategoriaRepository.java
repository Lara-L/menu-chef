package com.system.MenuChef.infra.repository;

import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import com.system.MenuChef.domain.entity.Categoria;
import com.system.MenuChef.domain.repository.CategoriaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PostgresCategoriaRepository implements CategoriaRepository {

  private final SpringDataCategoriaRepository springDataCategoriaRepository;

  public PostgresCategoriaRepository(SpringDataCategoriaRepository springDataCategoriaRepository) {
    this.springDataCategoriaRepository = springDataCategoriaRepository;
  }

  @Override
  public Optional<Categoria> findCategoriaByName(String name) {
    return springDataCategoriaRepository.findCategoriaByName(name);
  }

  @Override
  public void delete(Categoria categoria) {
    springDataCategoriaRepository.delete(categoria);
  }

  @Override
  public PageTO<Categoria> findAll(PaginationTO paginationTO) {
    Pageable pageable = PageRequest.of(paginationTO.getPage(), paginationTO.getSize());
    Page<Categoria> categories = springDataCategoriaRepository.findAll(pageable);
    return new PageTO<>(categories.getContent(), categories.getTotalElements(), categories.getNumber(),
        categories.getSize());
  }

  @Override
  public Categoria save(Categoria categoria) {
    return springDataCategoriaRepository.save(categoria);
  }

  @Override
  public Optional<Categoria> findById(UUID id) {
    return springDataCategoriaRepository.findById(id);
  }
}
