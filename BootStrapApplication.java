package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * p2p bootstrap application
 * @author tony
 *
 */

@SpringBootApplication
@ComponentScan("com")
@EnableAsync
public class BootStrapApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BootStrapApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootStrapApplication.class);
	}
}
