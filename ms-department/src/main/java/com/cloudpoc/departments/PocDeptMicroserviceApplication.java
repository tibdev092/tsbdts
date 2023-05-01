package com.cloudpoc.departments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type= EnableHypermediaSupport.HypermediaType.HAL)
@EnableEurekaClient
public class PocDeptMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PocDeptMicroserviceApplication.class, args);
	}
}
