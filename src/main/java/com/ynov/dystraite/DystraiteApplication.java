package com.ynov.dystraite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DystraiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DystraiteApplication.class, args);
	}
}
