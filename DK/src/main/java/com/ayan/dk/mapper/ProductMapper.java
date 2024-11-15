package com.ayan.dk.mapper;

import com.ayan.dk.dto.ProductRequest;
import com.ayan.dk.dto.ProductResponse;
import com.ayan.dk.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .productName(request.productName())
                .price(request.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getProductName(), product.getPrice());
    }
}