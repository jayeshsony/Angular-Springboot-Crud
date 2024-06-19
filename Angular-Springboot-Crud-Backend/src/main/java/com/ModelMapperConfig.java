package com;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper bean creation.
 * 
 * This class provides configuration for creating a ModelMapper bean, which is
 * used for mapping between different Java objects.
 * 
 * @author Jayesh Soni
 * @since 2024-06-03
 */
@Configuration
public class ModelMapperConfig {

	/**
	 * Creates and configures a ModelMapper bean.
	 * 
	 * @return A configured ModelMapper instance.
	 */
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}