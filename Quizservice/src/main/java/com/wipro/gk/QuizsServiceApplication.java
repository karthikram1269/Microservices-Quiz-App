package com.wipro.gk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizsServiceApplication.class, args);
	}

}
