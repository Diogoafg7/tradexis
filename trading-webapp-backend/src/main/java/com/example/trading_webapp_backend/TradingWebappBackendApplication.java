package com.example.trading_webapp_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradingWebappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingWebappBackendApplication.class, args);
	}

}
