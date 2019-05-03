package com.authright.happyPrime.service.number;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;


public interface HappyPrimeService {

	 boolean isHappyPrime(int number);
}
