package com.authright.happyPrime.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.authright.happyPrime.exception.HappyPrimeCheckException;

import java.io.IOException;

public class HttpClientResponseErrorHandler extends DefaultResponseErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(HttpClientResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        LOG.error("Response error: {}", response.getStatusCode());
        throw new HappyPrimeCheckException("Error when calling Rest api. Status: " + response.getStatusCode()+", detail:"+response);
    }
}
