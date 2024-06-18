package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to bootstrap the AngularSpringbootCrudApplication.
 * 
 * This class serves as the entry point for the Spring Boot application. It
 * initializes the Spring context and starts the application.
 * 
 * @author Jayesh Soni
 * @since 2024-06-04
 */
@SpringBootApplication
public class AngularSpringbootCrudApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AngularSpringbootCrudApplication.class, args);

	}

}