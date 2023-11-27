package com.udemy.orderservice.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "amount")
    private long amount;


}
