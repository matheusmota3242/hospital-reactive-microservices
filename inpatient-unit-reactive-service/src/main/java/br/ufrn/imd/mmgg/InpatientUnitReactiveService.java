package br.ufrn.imd.mmgg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InpatientUnitReactiveService {
    public static void main(String[] args) {
        SpringApplication.run(InpatientUnitReactiveService.class, args);
    }
}
