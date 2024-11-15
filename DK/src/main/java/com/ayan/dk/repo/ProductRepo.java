package com.ayan.dk.repo;

import com.ayan.dk.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    List<Product> findByPriceBetweenOrderByPriceDesc(@Param("minPrice") double minPrice,
                                               @Param("maxPrice") double maxPrice,
                                               Pageable pageable);
}
