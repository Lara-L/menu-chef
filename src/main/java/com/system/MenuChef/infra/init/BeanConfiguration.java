package com.system.MenuChef.infra.init;

import com.system.MenuChef.domain.repository.CategoriaRepository;
import com.system.MenuChef.domain.service.CategoriaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
  @Bean
  CategoriaService categoriaService(CategoriaRepository categoriaRepository) {
    return new CategoriaService(categoriaRepository);
  }
}
