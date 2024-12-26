package com.portal.testcontainer.controller;

import com.portal.testcontainer.dto.CustomerVO;
import com.portal.testcontainer.model.Customer;
import com.portal.testcontainer.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customer")
    public Customer getCustomerByName(@RequestBody CustomerVO customerVO) {
        return customerService.getCustomerByName(customerVO.name());
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody CustomerVO customer) {
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCustomer(@RequestBody CustomerVO customer) {
        customerService.updateCustomer(customer);
    }
}
