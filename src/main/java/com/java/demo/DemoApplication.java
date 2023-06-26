package com.java.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@RestController
class CustomerHttpController {
	private final CustomerRepository customerRepository;

	CustomerHttpController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/customers")
	Flux<Customer> customers() {
		return this.customerRepository.findAll();
	}
}

interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {}

record Customer(@Id Integer id, String name) {}
