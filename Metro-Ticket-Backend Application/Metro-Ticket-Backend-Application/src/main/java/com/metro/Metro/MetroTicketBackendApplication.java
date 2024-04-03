package com.metro.Metro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.metro.Metro.service","com.metro.Metro.utils","com.metro.Metro.configuration"})
public class MetroTicketBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroTicketBackendApplication.class, args);
		
	}

}
