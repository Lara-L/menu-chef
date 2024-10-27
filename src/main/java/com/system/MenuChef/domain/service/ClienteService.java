package com.system.MenuChef.domain.service;

import com.system.MenuChef.domain.TO.ClienteTO;
import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import com.system.MenuChef.domain.entity.Cliente;
import com.system.MenuChef.domain.exceptions.DomainException;
import com.system.MenuChef.domain.exceptions.ErrorCode;
import com.system.MenuChef.domain.repository.ClienteRepository;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public PageTO<Cliente> getAll(PaginationTO paginationTO) {
    return clienteRepository.findAll(paginationTO);
  }

  public Cliente getById(UUID id) throws DomainException {
    Optional<Cliente> client = clienteRepository.findById(id);
    if(client.isEmpty()) {
      throw new DomainException(ErrorCode.CLIENT_NOT_FOUND);
    }
    return client.get();
  }

  public Cliente createClient(ClienteTO clientTO) throws DomainException {
    Optional<Cliente> clientDB = clienteRepository.findClienteByDesk(clientTO.getDesk());
    if(clientDB.isPresent()) {
      throw new DomainException(ErrorCode.CLIENT_EXISTENT);
    }

    Cliente client = Cliente.builder()
        .name(clientTO.getName())
        .phone(clientTO.getPhone())
        .email(clientTO.getEmail())
        .desk(clientTO.getDesk())
        .build();
    return clienteRepository.save(client);
  }

  public Cliente updateClient(ClienteTO clientTO, UUID id) throws DomainException {
    Cliente client = this.getById(id);

    client.setName(clientTO.getName());
    client.setPhone(clientTO.getPhone());
    client.setEmail(clientTO.getEmail());
    client.setDesk(clientTO.getDesk());

    return clienteRepository.save(client);
  }

  public void deleteClient(UUID id) throws DomainException {
    Cliente client = this.getById(id);

    client.setDeletedAt(new Date());
    client.setUpdatedAt(new Date());

    clienteRepository.save(client);
  }
}
