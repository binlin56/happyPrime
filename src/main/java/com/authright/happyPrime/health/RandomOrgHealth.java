package com.authright.happyPrime.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.authright.happyPrime.config.propertyconfig.RandomPropConfig;

import java.net.URI;

@Component
public class RandomOrgHealth implements HealthIndicator{
	@Autowired
    private RandomPropConfig randomPropConfig;
	    
	@Override
	public Health health() {
		try {
			RestTemplate rest = new RestTemplate();
			rest.getForObject(" https://www.random.org", String.class);
			return Health.up().build();
			
		}catch (Exception e) {
			return Health.down().build();
		}
	}

 
}
