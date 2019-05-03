package com.authright.happyPrime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HappyPrimeCheckApplication {
	private static final Logger LOG = LogManager.getLogger(HappyPrimeCheckApplication.class);
	
	public static void main(String[] args) {
		LOG.info("Starting Application");
		SpringApplication.run(HappyPrimeCheckApplication.class, args);
	}

}
