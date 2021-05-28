package com.gmail.andreygritsevich.service.util;

import com.gmail.andreygritsevich.repository.model.Order;
import com.gmail.andreygritsevich.service.model.OrderDTO;

public class OrderConverterUtil {

    public static OrderDTO getDTOFromObject(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setNumber(order.getNumber());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setItemDTO(ItemConverterUtil.getDTOFromObject(order.getItem()));
        orderDTO.setUserDTO(UserConverterUtil.getDTOFromObject(order.getUser()));
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setAmount(order.getAmount());
        return orderDTO;
    }

    public static Order getObjectFromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setNumber(orderDTO.getNumber());
        order.setDate(orderDTO.getDate());
        order.setStatus(orderDTO.getStatus());
        order.setItem(ItemConverterUtil.getObjectFromDTO(orderDTO.getItemDTO()));
        order.setUser(UserConverterUtil.getObjectFromDTO(orderDTO.getUserDTO()));
        order.setQuantity(orderDTO.getQuantity());
        order.setAmount(orderDTO.getAmount());
        return order;
    }

}
