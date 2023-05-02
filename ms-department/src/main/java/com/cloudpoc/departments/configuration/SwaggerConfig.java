package com.cloudpoc.departments.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Spring Cloud Poc")
                .version("1")
                .description("Department Services")
                .termsOfService("http://swagger.io/terms")
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org")));
    }
}
