package com.banigeo.webpoc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public SimpleMappingExceptionResolver getResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setDefaultErrorView("errorDefault");
        resolver.setExceptionAttribute("exception");

        return resolver;
    }

}
