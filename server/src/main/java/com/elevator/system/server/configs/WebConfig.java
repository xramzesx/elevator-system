package com.elevator.system.server.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**")
                .allowedOrigins( "*" )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true).maxAge(3600);

        System.out.println("CORS enabled");
        // Add more mappings...
    }
}

