package com.banigeo.webpoc;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@AllArgsConstructor
@EnableFeignClients
@EnableEurekaClient
public class WebpocApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebpocApplication.class, args);
    }
}
