package com.authright.happyPrime.service.rest.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.authright.happyPrime.service.rest.RandomGeneratorRestService;

import java.net.URI;

@Service("randomGeneratorRestService")
public class RandomGeneratorRestServiceImpl implements RandomGeneratorRestService {

    private static final Logger LOG = LogManager.getLogger(RandomGeneratorRestServiceImpl.class);


    @Autowired
    @Qualifier("randomGeneratorRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> postRequest(HttpEntity<?> requestEntity, URI uri) {
        LOG.info(" POST Request: " + requestEntity.toString());
        ResponseEntity<String> randomCheckPostResponse = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        LOG.info("random generator POST Response: " + randomCheckPostResponse);
        return randomCheckPostResponse;
    }

    @Override
    public ResponseEntity<String> getRequest(HttpEntity<?> requestEntity, URI uri) {
        LOG.info("random generator GET Request: " + requestEntity.toString());
        ResponseEntity<String> randomCheckGetResponse = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        LOG.info("random generator GET Response: " + randomCheckGetResponse);
        return randomCheckGetResponse;
    }
    
    @Override
    public ResponseEntity<String> putRequest(HttpEntity<?> requestEntity, URI uri) {
        LOG.info("random generator PUT Request: " + requestEntity.toString());
        ResponseEntity<String> randomCheckPostResponse = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
        LOG.info("random generator PUT Response: " + randomCheckPostResponse);
        return randomCheckPostResponse;
    }
}
