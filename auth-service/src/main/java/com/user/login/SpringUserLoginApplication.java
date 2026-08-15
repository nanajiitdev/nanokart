package com.user.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringUserLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUserLoginApplication.class, args);
	}

}
