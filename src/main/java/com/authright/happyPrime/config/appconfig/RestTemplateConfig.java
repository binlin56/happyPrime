package com.authright.happyPrime.config.appconfig;

import com.authright.happyPrime.config.propertyconfig.RestTemplatePropConfig;
import com.authright.happyPrime.handler.HttpClientResponseErrorHandler;
import com.google.common.collect.Lists;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@EnableConfigurationProperties(RestTemplatePropConfig.class)
@ComponentScan(value = "com.authright.happyPrime")
public class RestTemplateConfig {
	
	@Autowired
	private RestTemplatePropConfig restTemplatePropConfig;

    
    @Bean("randomGeneratorRestTemplate")
    @Scope("prototype")
    public RestTemplate getPingFedRestTemplate() {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new HttpClientResponseErrorHandler());

            BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(httpRequestFactory());

            restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);

            List<ClientHttpRequestInterceptor> interceptors = Lists.newArrayList(new LogRequestResponseFilter());
            restTemplate.setInterceptors(interceptors);

            restTemplate.setInterceptors(interceptors);
            return restTemplate;
    }
    
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpClientRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        httpClientRequestFactory.setConnectTimeout(restTemplatePropConfig.getConnectTimeOut());
        httpClientRequestFactory.setReadTimeout(restTemplatePropConfig.getReadTimeOut());
        return httpClientRequestFactory;
    }

    @Bean
    public HttpClient httpClient() {

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(restTemplatePropConfig.getPool().getSize());
        connectionManager.setDefaultMaxPerRoute(restTemplatePropConfig.getPool().getMaxPerRoute());
        return HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .build();

    }


    @Autowired
    public void setRestTemplatePropConfig(RestTemplatePropConfig restTemplatePropConfig) {
        this.restTemplatePropConfig = restTemplatePropConfig;
    }
}
