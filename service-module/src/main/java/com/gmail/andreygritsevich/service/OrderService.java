package com.gmail.andreygritsevich.service;

import java.util.List;

import com.gmail.andreygritsevich.service.model.OrderDTO;
import com.gmail.andreygritsevich.service.model.OrderNewDTO;
import com.gmail.andreygritsevich.service.model.OrderStatusUpdateDTO;

public interface OrderService {

    OrderDTO add(OrderNewDTO orderNewDTO);

    Long getCountOrder();

    List<OrderDTO> getOrdersByPageSorted(Integer page);

    OrderDTO findById(Long id);

    OrderDTO changeOrderStatus(OrderStatusUpdateDTO orderStatusUpdateDTO);

    Long getCountOrderByUser(String userEmail);

    List<OrderDTO> getOrdersByPageSortedByUser(Integer page, String userEmail);

}
