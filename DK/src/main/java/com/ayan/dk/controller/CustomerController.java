package com.ayan.dk.controller;

import com.ayan.dk.dto.CustomerRequest;
import com.ayan.dk.dto.CustomerResponse;
import com.ayan.dk.dto.CustomerUpdateRequest;
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

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> updateCustomerDetails(@PathVariable String email,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody @Valid CustomerUpdateRequest updateRequest) {

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        String responseMessage = customerService.updateCustomerDetails(token, email, updateRequest);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email,
            @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        String responseMessage = customerService.deleteCustomer(email,token);
        return ResponseEntity.ok(responseMessage);
    }
}
