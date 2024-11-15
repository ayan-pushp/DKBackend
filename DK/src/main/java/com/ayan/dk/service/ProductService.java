package com.ayan.dk.service;

import com.ayan.dk.dto.ProductRequest;
import com.ayan.dk.dto.ProductResponse;
import com.ayan.dk.entity.Product;
import com.ayan.dk.exception.ProductNotFoundException;
import com.ayan.dk.mapper.ProductMapper;
import com.ayan.dk.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static java.lang.String.format;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        productRepo.save(product);
        return "Product Created Successfully";
    }

    public ProductResponse getProduct(String name) {
        Product product = productRepo.findByProductName(name)
                .orElseThrow(() -> new ProductNotFoundException(
                        format("Cannot find Product:: No product found with the provided name:: %s", name)
                ));
        return productMapper.toProductResponse(product);
    }

}
