package com.ayan.dk.controller;

import com.ayan.dk.dto.CustomerRequest;
import com.ayan.dk.dto.CustomerResponse;
import com.ayan.dk.dto.CustomerUpdateRequest;
import com.ayan.dk.dto.LoginRequest;
import com.ayan.dk.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }

    @PatchMapping("/update/{email}")
    public ResponseEntity<String> updateCustomerDetails(@PathVariable String email,
                                                        @RequestBody @Valid CustomerUpdateRequest request) {
        String responseMessage = customerService.updateCustomerDetails(email, request);
        return ResponseEntity.ok(responseMessage);
    }
}
