package org.reactive.management.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableR2dbcRepositories(basePackages = "org.reactive.management.service.repository")
@SpringBootApplication
@EnableWebFlux
public class ReactiveManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveManagementServiceApplication.class, args);
	}
}
