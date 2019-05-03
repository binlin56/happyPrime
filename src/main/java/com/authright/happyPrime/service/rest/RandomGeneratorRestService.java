package com.authright.happyPrime.service.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;


public interface RandomGeneratorRestService {

    ResponseEntity<String> postRequest(HttpEntity<?> requestEntity, URI uri);

    ResponseEntity<String> getRequest(HttpEntity<?> requestEntity, URI uri);
    
    ResponseEntity<String> putRequest(HttpEntity<?> requestEntity, URI uri);
}
