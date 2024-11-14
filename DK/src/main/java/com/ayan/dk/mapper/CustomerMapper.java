package com.ayan.dk.mapper;

import org.springframework.stereotype.Service;

import com.ayan.dk.dto.CustomerRequest;
import com.ayan.dk.dto.CustomerResponse;
import com.ayan.dk.entity.Customer;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
