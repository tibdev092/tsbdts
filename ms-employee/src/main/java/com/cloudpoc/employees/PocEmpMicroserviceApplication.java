package com.cloudpoc.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type= EnableHypermediaSupport.HypermediaType.HAL)
@EnableEurekaClient
@EnableFeignClients
public class PocEmpMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PocEmpMicroserviceApplication.class, args);
	}
}
