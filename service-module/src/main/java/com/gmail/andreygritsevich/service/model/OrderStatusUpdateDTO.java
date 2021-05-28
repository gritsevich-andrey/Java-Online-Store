package com.gmail.andreygritsevich.service.model;

import com.gmail.andreygritsevich.repository.model.OrderStatusEnum;

public class OrderStatusUpdateDTO {

    private Long id;
    private OrderStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

}
