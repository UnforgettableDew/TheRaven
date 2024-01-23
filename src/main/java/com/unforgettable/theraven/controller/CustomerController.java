package com.unforgettable.theraven.controller;

import com.unforgettable.theraven.dto.CreateCustomerDTO;
import com.unforgettable.theraven.dto.CustomerDTO;
import com.unforgettable.theraven.dto.UpdateCustomerDTO;
import com.unforgettable.theraven.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/api/customers/{id}")
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("/api/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CreateCustomerDTO createCustomerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(createCustomerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id,
                                                      @RequestBody @Valid UpdateCustomerDTO updateCustomerDTO) {
        return new ResponseEntity<>(customerService.updateCustomerById(id, updateCustomerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
