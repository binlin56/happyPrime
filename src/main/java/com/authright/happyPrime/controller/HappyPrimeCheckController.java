package com.authright.happyPrime.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.authright.happyPrime.service.number.HappyPrimeService;
import com.authright.happyPrime.service.rest.RandomGeneratorRestService;
import com.authright.happyPrime.service.rest.impl.RandomGeneratorRestServiceImpl;
import com.authright.happyPrime.config.propertyconfig.RandomPropConfig;
import com.authright.happyPrime.model.ChecKResult;
import com.authright.happyPrime.model.NumberType;

@RestController
@RequestMapping(value = "/rest")
public class HappyPrimeCheckController extends RandomGeneratorRestServiceImpl {

    private static final Logger LOG = LogManager.getLogger(HappyPrimeCheckController.class);
    
    @Autowired
	private RandomPropConfig randomPropConfig;
    
    
    @Autowired
    @Qualifier("randomGeneratorRestService")
    private RandomGeneratorRestService randomGeneratorRestService;

    @Autowired
    @Qualifier("happyPrimeService")
    private HappyPrimeService happyPrimeService;

    @RequestMapping(value = "v1/happyprime", method = RequestMethod.GET)
    public
    @ResponseBody
    ChecKResult checkHappyPrime() {
        LOG.info("Start check checkHappyPrime");
        
        URI uri = null;
        ChecKResult checkResult =new ChecKResult();
        try {
            String endpointUrl = randomPropConfig.getBaseUrl()+"/?"+randomPropConfig.getQueryParameter();
            LOG.info("endpointUrl:"+endpointUrl);
            uri = getUri(endpointUrl);
            HttpHeaders headers = getHeaders();
            HttpEntity<?> requestHttpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = getRequest(requestHttpEntity, uri);
            processResponse(responseEntity, checkResult);
        } catch (Exception exception) {
            processException(exception, uri);
            checkResult.setMessage("Exception:"+ exception.getMessage());;
        }
        
        return checkResult;
    }
    
    public void processResponse(ResponseEntity<String> responseEntity, ChecKResult checkResult) {
    	NumberType type;
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            String responseString = responseEntity.getBody();
            LOG.info("Response body: " + responseString);
            String cleanNumString = responseString.replaceAll("[^\\d]", "" );
            LOG.debug("cleanNumString: " + cleanNumString);
            int randomNumber = Integer.parseInt(cleanNumString);	
            LOG.info("Random number: " + randomNumber);
            checkResult.setNumber(randomNumber);
           
            boolean isHappyPrime =happyPrimeService.isHappyPrime(randomNumber);
            type = isHappyPrime? NumberType.HAPPYPRIME: NumberType.SAD;
            checkResult.setType(type);
            checkResult.setMessage("number is determined successfully");
        } else {
            String errorResponseBodyString = responseEntity.getBody();
            LOG.error("Error making random check call: " + errorResponseBodyString);
            checkResult.setMessage(errorResponseBodyString); 
        }
    }


    public void processException(Exception e, URI uri) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientException = (HttpClientErrorException) e;
            String errorResponseBodyString = httpClientException.getResponseBodyAsString();
            LOG.error("HttpClientException making random check call: " + uri.toString() + ". " +
                    "\nError Response: " + errorResponseBodyString);

        } else if (e instanceof HttpServerErrorException) {
            HttpServerErrorException httpServerErrorException = (HttpServerErrorException) e;
            String errorResponseBodyString = httpServerErrorException.getResponseBodyAsString();
            LOG.error("HttpServerErrorException making random check call: " + uri.toString() + ". " +
                    "\nError Response: " + errorResponseBodyString);
        } else {
            LOG.error("Exception making random check call: " + uri.toString(), e);
        }
    }

    public URI getUri(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            LOG.error("Exception during creating URI: " +url);
            throw new RuntimeException("Exception during creating URI: " + randomPropConfig.getBaseUrl());
        }
        return uri;
    }
    
    public HttpHeaders getHeaders() {
        return new HttpHeaders() {

            private static final long serialVersionUID = 4732206229842570018L;

            {
                List<MediaType> acceptTypes = new ArrayList<>();
                acceptTypes.add(MediaType.APPLICATION_JSON_UTF8);
                setAccept(acceptTypes);
                setContentType(MediaType.APPLICATION_JSON_UTF8);
            }
        };
    }

}
