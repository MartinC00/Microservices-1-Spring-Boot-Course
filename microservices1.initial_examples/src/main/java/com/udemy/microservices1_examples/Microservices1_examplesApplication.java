package com.udemy.microservices1_examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "controllers") //useful for load app context with different base package than the original one
public class Microservices1_examplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Microservices1_examplesApplication.class, args);
	}

}
