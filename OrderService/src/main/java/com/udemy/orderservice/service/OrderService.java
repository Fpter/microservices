package com.udemy.orderservice.service;

import com.udemy.orderservice.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
