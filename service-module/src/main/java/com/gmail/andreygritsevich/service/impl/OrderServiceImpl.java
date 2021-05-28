package com.gmail.andreygritsevich.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.gmail.andreygritsevich.repository.ItemRepository;
import com.gmail.andreygritsevich.repository.OrderRepository;
import com.gmail.andreygritsevich.repository.UserRepository;
import com.gmail.andreygritsevich.repository.model.Order;
import com.gmail.andreygritsevich.repository.model.OrderStatusEnum;
import com.gmail.andreygritsevich.service.OrderService;
import com.gmail.andreygritsevich.service.model.OrderDTO;
import com.gmail.andreygritsevich.service.model.OrderNewDTO;
import com.gmail.andreygritsevich.service.model.OrderStatusUpdateDTO;
import com.gmail.andreygritsevich.service.util.OrderConverterUtil;
import com.gmail.andreygritsevich.service.util.PaginationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository,
            ItemRepository itemRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public OrderDTO add(OrderNewDTO orderNewDTO) {
        Order order = new Order();
        order.setUser(userRepository.getUserByEmail(orderNewDTO.getUserEmail()));
        order.setItem(itemRepository.findById(orderNewDTO.getItemId()));
        if (order.getUser() == null || order.getItem() == null) {
            return null;
        }
        order.setQuantity(orderNewDTO.getQuantity());
        order.setAmount(order.getItem().getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatusEnum.NEW);
        orderRepository.persist(order);
        order.setNumber(order.getId());
        return getDTOFromObject(order);
    }

    @Override
    @Transactional
    public Long getCountOrder() {
        return orderRepository.getCount();
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrdersByPageSorted(Integer page) {
        int startPosition = PaginationUtil.getPositionByPage(page);
        List<Order> orders = orderRepository.getItemsByPageSorted(startPosition, PaginationUtil.ITEMS_BY_PAGE);
        return convertItemsToItemsDTO(orders);
    }

    @Override
    @Transactional
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            return null;
        }
        return getDTOFromObject(order);
    }

    @Override
    @Transactional
    public OrderDTO changeOrderStatus(OrderStatusUpdateDTO orderStatusUpdateDTO) {
        Order order = orderRepository.findById(orderStatusUpdateDTO.getId());
        if (order == null) {
            return null;
        }
        order.setStatus(orderStatusUpdateDTO.getStatus());
        orderRepository.merge(order);
        return getDTOFromObject(order);
    }

    @Override
    @Transactional
    public Long getCountOrderByUser(String userEmail) {
        return orderRepository.getCountByUser(userEmail);
    }

    @Override
    public List<OrderDTO> getOrdersByPageSortedByUser(Integer page, String userEmail) {
        int startPosition = PaginationUtil.getPositionByPage(page);
        List<Order> orders = orderRepository.getItemsByPageSortedByUser(startPosition, PaginationUtil.ITEMS_BY_PAGE, userEmail);
        return convertItemsToItemsDTO(orders);
    }

    private List<OrderDTO> convertItemsToItemsDTO(List<Order> orders) {
        return orders.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
    }

    private OrderDTO getDTOFromObject(Order order) {
        return OrderConverterUtil.getDTOFromObject(order);
    }

    private Order getObjectFromDTO(OrderDTO orderDTO) {
        return OrderConverterUtil.getObjectFromDTO(orderDTO);
    }

}
