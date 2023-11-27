package com.udemy.productservice.service;

import com.udemy.productservice.model.ProductRequest;
import com.udemy.productservice.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    void reduceQuantity(long productId, long quantity);
}
