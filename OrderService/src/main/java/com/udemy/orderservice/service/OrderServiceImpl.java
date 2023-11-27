package com.udemy.orderservice.service;

import com.udemy.orderservice.entity.Order;
import com.udemy.orderservice.external.client.ProductService;
import com.udemy.orderservice.model.OrderRequest;
import com.udemy.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Place order request {}", orderRequest.toString());
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        Order order = Order.builder()
                .orderStatus("CREATED")
                .quantity(orderRequest.getQuantity())
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .orderDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        order = orderRepository.save(order);
        log.info("Order save with id = " + order.getId());
        return order.getId();
    }
}
