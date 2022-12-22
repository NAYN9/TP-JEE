package org.sid.costomerservice;

import org.sid.costomerservice.Repository.CustomerRepository;
import org.sid.costomerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CostomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner Start(CustomerRepository cr, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			cr.save(new Customer(null,"Ayoub","test1@gmail.com"));
			cr.save(new Customer(null,"walid","test2@gmail.com"));
			cr.save(new Customer(null,"imad","test3@gmail.com"));
			cr.findAll().forEach(customer -> {
				System.out.println(customer.toString());
			});
		};
	}
}
