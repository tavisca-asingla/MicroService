package com.tavisca.micro.datavalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DataValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataValidatorApplication.class, args);
	}

}
