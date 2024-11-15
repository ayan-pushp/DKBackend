package com.ayan.dk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static java.lang.String.format;

import com.ayan.dk.dto.CustomerRequest;
import com.ayan.dk.dto.CustomerUpdateRequest;
import com.ayan.dk.dto.CustomerResponse;
import com.ayan.dk.dto.LoginRequest;
import com.ayan.dk.entity.Customer;
import com.ayan.dk.exception.CustomerNotFoundException;
import com.ayan.dk.helper.EncryptionService;
import com.ayan.dk.helper.JWTHelper;
import com.ayan.dk.mapper.CustomerMapper;
import com.ayan.dk.repo.CustomerRepo;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return "Customer logged in successfully!\n Here's the token: "+jwtHelper.generateToken(request.email());
    }

    public String deleteCustomer(String token, String email) {

        if (email == null || !jwtHelper.validateToken(token, email)) {
            return "You do not have permission to perform this action";
        }

        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot delete Customer:: No customer found with the provided email:: %s", email)
                ));

        customerRepo.delete(customer);

        return "Customer deleted successfully";
    }

    public String updateCustomerDetails(String token, String email, CustomerUpdateRequest updateRequest) {

        if (email == null || !jwtHelper.validateToken(token, email)) {
            return "You do not have permission to perform this action";
        }

        Customer customer = customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with email %s not found", email)
                ));

        if (updateRequest.firstName() != null) {
            customer.setFirstName(updateRequest.firstName());
        }
        if (updateRequest.lastName() != null) {
            customer.setLastName(updateRequest.lastName());
        }
        if (updateRequest.mobile() != null) {
            customer.setMobile(updateRequest.mobile());
        }
        if (updateRequest.address() != null) {
            customer.setAddress(updateRequest.address());
        }
        if (updateRequest.pincode() != null) {
            customer.setPincode(updateRequest.pincode());
        }

        customerRepo.save(customer);
        return "Customer details updated successfully";
    }
}

