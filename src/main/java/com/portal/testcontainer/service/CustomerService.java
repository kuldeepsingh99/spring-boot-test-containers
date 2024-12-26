package com.portal.testcontainer.service;

import com.portal.testcontainer.dto.CustomerVO;
import com.portal.testcontainer.model.Customer;
import com.portal.testcontainer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByName(String customerName) {
        return customerRepository.findByName(customerName)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    public void saveCustomer(CustomerVO customerVO) {
        Customer customer = new Customer();
        customer.setName(customerVO.name());
        customer.setAddress(customerVO.address());
        customer.setAge(customerVO.age());
        customer.setEmail(customerVO.email());
        customer.setPhone(customerVO.phone());
        customer.setSalary(customerVO.salary());
        customer.setActive(customerVO.active());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public void updateCustomer(CustomerVO customerVO) {
        Customer existingCustomer = customerRepository.findById(customerVO.id())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        updateIfNotNullOrEmpty(customerVO.name(), existingCustomer::setName);
        updateIfNotNullOrEmpty(customerVO.address(), existingCustomer::setAddress);
        updateIfNotNull(customerVO.age(), existingCustomer::setAge);
        updateIfNotNullOrEmpty(customerVO.email(), existingCustomer::setEmail);
        updateIfNotNullOrEmpty(customerVO.phone(), existingCustomer::setPhone);
        updateIfNotNull(customerVO.salary(), existingCustomer::setSalary);
        updateIfNotNull(customerVO.active(), existingCustomer::setActive);

        customerRepository.save(existingCustomer);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void updateIfNotNullOrEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }
}
