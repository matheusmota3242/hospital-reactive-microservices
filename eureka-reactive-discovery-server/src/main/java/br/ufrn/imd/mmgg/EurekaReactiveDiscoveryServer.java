package br.ufrn.imd.mmgg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaReactiveDiscoveryServer {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaReactiveDiscoveryServer.class, args);
    }
}
