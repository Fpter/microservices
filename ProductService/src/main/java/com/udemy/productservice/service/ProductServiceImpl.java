package com.udemy.productservice.service;

import com.udemy.productservice.entity.Product;
import com.udemy.productservice.exception.ProductServiceCustomException;
import com.udemy.productservice.model.ProductRequest;
import com.udemy.productservice.model.ProductResponse;
import com.udemy.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest request) {
        log.info("Add product ...");
        Product product = Product.builder()
                .productName(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Get product ...");
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductServiceCustomException("Product not found " + id, "404"));
        ProductResponse productResponse = ProductResponse.builder()
//                .productId(id)
//                .price(product.getPrice())
//                .quantity(product.getQuantity())
//                .productName(product.getProductName())
                .build();
        log.info("Got product");
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {} for Id: {}", quantity, productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));
        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity updated successfully");
    }
}
