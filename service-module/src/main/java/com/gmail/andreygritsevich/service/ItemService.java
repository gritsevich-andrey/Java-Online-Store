package com.gmail.andreygritsevich.service;

import java.util.List;

import com.gmail.andreygritsevich.service.model.ItemDTO;

public interface ItemService {

    ItemDTO add(ItemDTO itemDTO);

    Long getCountItem();

    List<ItemDTO> getItemsByPageSorted(Integer page);

    boolean deleteById(Long id);

    ItemDTO findById(Long id);

    ItemDTO copyById(Long itemId);

    int addItemsAsJSON(String pathFile);

}
