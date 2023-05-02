package com.couldpoc.pocnamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PocNamingServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PocNamingServerApplication.class, args);
	}
}
