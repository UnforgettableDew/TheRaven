package com.unforgettable.theraven.service;

import com.unforgettable.theraven.dto.CreateCustomerDTO;
import com.unforgettable.theraven.dto.CustomerDTO;
import com.unforgettable.theraven.dto.UpdateCustomerDTO;
import com.unforgettable.theraven.entity.Customer;
import com.unforgettable.theraven.exception.CustomerNotFoundException;
import com.unforgettable.theraven.mapper.CustomerMapper;
import com.unforgettable.theraven.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToDTO)
                .toList();
    }

    public CustomerDTO findCustomerById(Long id) {
        return customerMapper.mapToDTO(getCustomer(id));
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = customerMapper.mapToEntity(createCustomerDTO);
        return customerMapper.mapToDTO(customerRepository.save(customer));
    }


    public CustomerDTO updateCustomerById(Long id, UpdateCustomerDTO updateCustomerDTO) {
        Customer customer = getCustomer(id);

        customer.setFullName(updateCustomerDTO.getFullName());
        customer.setPhone(updateCustomerDTO.getPhone());
        customer.setUpdated(System.currentTimeMillis());

        return customerMapper.mapToDTO(customerRepository.save(customer));

    }

    private Customer getCustomer(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer with ID = " + id + " not found"));
    }

    public void deleteCustomerById(Long id) {
        Customer customer = getCustomer(id);

        customer.setIsActive(false);
        customerRepository.save(customer);
    }
}
