package com.java.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	CustomerRepository customerRepository;

	@Test
	void contextLoads() {
		Flux<Customer> customers = customerRepository.findAll(); // James, Josh, Trisha
		customerRepository.save(new Customer(null, "Hadi")).subscribe();
		Assertions.assertNotNull( customers.last().map( customer -> customer.id()).block());
		Assertions.assertNotNull( customers.last().map( customer -> customer.name()).block());
		Assertions.assertEquals( "Hadi", customers.last().map(customer -> customer.name()).block());
	}

}
