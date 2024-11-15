package com.ayan.dk.controller;

import com.ayan.dk.dto.CustomerResponse;
import com.ayan.dk.dto.ProductRequest;
import com.ayan.dk.dto.ProductResponse;
import com.ayan.dk.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.getProduct(name));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ProductResponse>> getTop2ProductsInRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<ProductResponse> list= productService.getTop2ProductsInPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(list);
    }
}
