package com.portal.testcontainer;

import com.portal.testcontainer.model.Customer;
import com.portal.testcontainer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestContainerConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
    void setUp() {
		customerRepository.deleteAll();

		List<Customer> customers = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			customers.add(Customer.builder()
					.name("Customer " + i)
					.email("customer" + i + "@example.com")
					.phone("100200" + i)
					.salary(50000.0 + i * 1000)
					.address(i + " Main St")
					.age(25 + i)
					.active(1)
					.build());
		}
		customerRepository.saveAll(customers);

	}

	@Test
	void testGetCustomers() throws Exception {
		mockMvc.perform(get("/customers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Customer 1"))
				.andExpect(jsonPath("$[0].email").value("customer1@example.com"));
	}

}
