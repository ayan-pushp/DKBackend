package com.ayan.dk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.ayan.dk.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}