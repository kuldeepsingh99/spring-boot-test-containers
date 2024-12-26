package com.portal.testcontainer.repository;



import com.portal.testcontainer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findByName(String name);
}