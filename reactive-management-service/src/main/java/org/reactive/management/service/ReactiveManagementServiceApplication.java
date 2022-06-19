package org.reactive.management.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackages = "org.reactive.management.service.repository")
@EnableEurekaClient
@SpringBootApplication
public class ReactiveManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveManagementServiceApplication.class, args);
	}
}
