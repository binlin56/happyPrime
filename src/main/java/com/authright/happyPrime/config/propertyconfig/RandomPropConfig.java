package com.authright.happyPrime.config.propertyconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("randomorg.api")
public class RandomPropConfig {

	private String baseUrl;
	private String queryParameter;
	

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	@Override
	public String toString() {
		return "RandomPropConfig [baseUrl=" + baseUrl + "]";
	}


	public String getQueryParameter() {
		return queryParameter;
	}


	public void setQueryParameter(String queryParameter) {
		this.queryParameter = queryParameter;
	}

}
