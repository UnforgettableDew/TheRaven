package com.unforgettable.theraven.repository;

import com.unforgettable.theraven.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
