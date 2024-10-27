package com.system.MenuChef.domain.service;

import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import com.system.MenuChef.domain.TO.PedidoTO;
import com.system.MenuChef.domain.entity.Cliente;
import com.system.MenuChef.domain.entity.Item;
import com.system.MenuChef.domain.entity.Pedido;
import com.system.MenuChef.domain.exceptions.DomainException;
import com.system.MenuChef.domain.exceptions.ErrorCode;
import com.system.MenuChef.domain.repository.PedidoRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ItemService itemService;
  private final ClienteService clienteService;

  public PedidoService(PedidoRepository pedidoRepository, ItemService itemService,
      ClienteService clienteService) {
    this.pedidoRepository = pedidoRepository;
    this.itemService = itemService;
    this.clienteService = clienteService;
  }

  public PageTO<Pedido> getAll(PaginationTO paginationTO) {
    return pedidoRepository.findAll(paginationTO);
  }

  public Pedido findById(UUID id) throws DomainException {
    return pedidoRepository.findById(id).orElseThrow(() -> new DomainException(ErrorCode.RENT_NOT_FOUND));
  }

  public Pedido createPedido(PedidoTO pedidoTO) throws DomainException {
    List<Item> itens = itemService.itensByIds(pedidoTO.getItensIds());
    Cliente cliente = clienteService.getById(pedidoTO.getClientId());

    Pedido pedido = Pedido.builder()
        .itens(itens)
        .client(cliente)
        .deskPedido(pedidoTO.getDeskPedido())
        .datePedido(pedidoTO.getDatePedido())
        .valueTotal(pedidoTO.getValueTotal())
        .build();

    return pedidoRepository.save(pedido);
  }

  public Pedido updatePedido(PedidoTO pedidoTO, UUID id) throws DomainException {
    Pedido pedido = this.findById(id);
    //mudar status do pedido
    return pedidoRepository.save(pedido);
  }

  public void deletePedido(UUID id) throws DomainException {
    Pedido pedido = this.findById(id);
  }
}
