package com.unforgettable.theraven.mapper;


import com.unforgettable.theraven.dto.CreateCustomerDTO;
import com.unforgettable.theraven.dto.CustomerDTO;
import com.unforgettable.theraven.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public Customer mapToEntity(CreateCustomerDTO createCustomerDTO){
        return Customer
                .builder()
                .fullName(createCustomerDTO.getFullName())
                .email(createCustomerDTO.getEmail())
                .phone(createCustomerDTO.getPhone())
                .created(System.currentTimeMillis())
                .isActive(true)
                .build();
    }

}
