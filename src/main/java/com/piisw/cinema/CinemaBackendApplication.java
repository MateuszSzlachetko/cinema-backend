package com.piisw.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CinemaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaBackendApplication.class, args);
	}

}
