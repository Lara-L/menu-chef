package com.system.MenuChef.domain.service;

import com.system.MenuChef.domain.TO.CategoriaTO;
import com.system.MenuChef.domain.TO.ItemTO;
import com.system.MenuChef.domain.TO.PageTO;
import com.system.MenuChef.domain.TO.PaginationTO;
import com.system.MenuChef.domain.entity.Categoria;
import com.system.MenuChef.domain.entity.Item;
import com.system.MenuChef.domain.exceptions.DomainException;
import com.system.MenuChef.domain.exceptions.ErrorCode;
import com.system.MenuChef.domain.repository.ItemRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.w3c.dom.DOMException;

public class ItemService {

  private final ItemRepository itemRepository;
  private final CategoriaService categoriaService;

  public ItemService(ItemRepository itemRepository, CategoriaService categoriaService) {
    this.itemRepository = itemRepository;
    this.categoriaService = categoriaService;
  }

  public PageTO<Item> getAll(PaginationTO paginationTO) {
    return itemRepository.findAll(paginationTO);
  }

  public Item findById(UUID id) throws DomainException {
    Optional<Item> item = itemRepository.findById(id);
    if (item.isEmpty()) {
      throw new DomainException(ErrorCode.ITEM_NOT_FOUND);
    }
    return item.get();
  }

  public List<Item> itensByIds(List<UUID> ids) throws DomainException {
    return ids.stream()
       .map(id -> {
         try {
           return itemRepository.findById(id).orElseThrow(() -> new DomainException(ErrorCode.ITEM_NOT_FOUND));
         } catch (DomainException e) {
           throw new RuntimeException(e);
         }
       })
       .collect(Collectors.toList());
  }

  public Item createItem(ItemTO itemTO) throws DomainException {
    verifyBeforeCreateItem(itemTO);

    Item item = Item.builder()
        .itemName(itemTO.getItemName())
        .itemPrice(itemTO.getItemPrice())
        .itemDesc(itemTO.getItemDesc())
        .category(itemTO.getCategory())
        .build();
    return itemRepository.save(item);
  }

  public Item updateItem(ItemTO itemTO, UUID id) throws DomainException {
    Item item = this.findById(id);

    item.setItemName(itemTO.getItemName());
    item.setItemDesc(itemTO.getItemDesc());
    item.setItemPrice(itemTO.getItemPrice());
    item.setCategory(itemTO.getCategory());

    return itemRepository.save(item);
  }

  public void deleteItem(UUID id) throws DomainException {
    Item item = this.findById(id);

    item.setDeletedAt(new Date());
    item.setUpdatedAt(new Date());

    itemRepository.save(item);
  }

  private void verifyBeforeCreateItem(ItemTO itemTO) throws DomainException {
    Optional<Item> itemName = itemRepository.findByName(itemTO.getItemName());
    if (itemName.isPresent()) {
      throw new DomainException(ErrorCode.ITEM_EXISTENT);
    }
  }

  public Item findByName(String itemName) throws DomainException {
    Optional<Item> item = itemRepository.findByName(itemName);

    if (item.isEmpty()) {
      throw new DomainException(ErrorCode.ITEM_NOT_FOUND);
    }
    return item.get();
  }

}
