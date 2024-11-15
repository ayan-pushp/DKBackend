package com.ayan.dk.repo;

import com.ayan.dk.entity.Customer;
import com.ayan.dk.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

}
