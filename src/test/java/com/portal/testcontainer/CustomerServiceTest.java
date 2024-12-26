package com.portal.testcontainer;


import com.portal.testcontainer.model.Customer;
import com.portal.testcontainer.repository.CustomerRepository;
import com.portal.testcontainer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void testGetCustomers() {
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getCustomers();
        assertEquals(2, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
        assertEquals("Jane Doe", customers.get(1).getName());
    }

}
