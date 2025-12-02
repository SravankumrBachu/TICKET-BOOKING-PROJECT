package com.admission.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application Configuration class
 * Defines Spring beans for the application
 */
@Configuration
public class ApplicationConfig {

    /**
     * ModelMapper bean for DTO conversion
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
