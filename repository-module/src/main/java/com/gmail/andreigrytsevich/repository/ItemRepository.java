package com.gmail.andreygritsevich.repository;

import java.util.List;

import com.gmail.andreygritsevich.repository.model.Item;

public interface ItemRepository extends GenericRepository<Long, Item> {

    List<Item> getItemsByPageSorted(int startPosition, int itemsByPage);

}
