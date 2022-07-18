package br.ufrn.imd.mmgg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackages = "br.ufrn.imd.mmgg.repository")
@EnableEurekaClient
@SpringBootApplication
public class ICUReactiveServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ICUReactiveServiceApplication.class, args);
    }
}
