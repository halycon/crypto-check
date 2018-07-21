package com.cryptocheck.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CryptoCheckBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoCheckBackendApplication.class, args);
	}
}
