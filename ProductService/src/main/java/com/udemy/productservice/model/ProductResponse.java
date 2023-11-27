package com.udemy.productservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
@Setter
@Getter
@Builder
public class ProductResponse {
    private Long productId;

    private String productName;

    private long price;

    private long quantity;
}
