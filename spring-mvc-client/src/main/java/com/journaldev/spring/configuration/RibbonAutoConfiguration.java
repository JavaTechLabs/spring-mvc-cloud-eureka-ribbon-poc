package com.journaldev.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.ClientFactory;
import com.netflix.niws.client.http.RestClient;

@Configuration
public class RibbonAutoConfiguration {

	@Bean
	public RestClient restClient() {
		return (RestClient) ClientFactory.getNamedClient("notification-service");
	}
}
