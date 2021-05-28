package com.gmail.andreygritsevich.repository;

import java.util.List;

import com.gmail.andreygritsevich.repository.model.Order;

public interface OrderRepository extends GenericRepository<Long, Order> {

    List<Order> getItemsByPageSorted(int startPosition, int itemsByPage);

    Long getCountByUser(String userEmail);

    List<Order> getItemsByPageSortedByUser(int startPosition, int itemsByPage, String userEmail);

}
