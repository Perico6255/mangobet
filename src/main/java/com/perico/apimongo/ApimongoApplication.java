package com.perico.apimongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan 
public class ApimongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApimongoApplication.class, args);
	}

}
