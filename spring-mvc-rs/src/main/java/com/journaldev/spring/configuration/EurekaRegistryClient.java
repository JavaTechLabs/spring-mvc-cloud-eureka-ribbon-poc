package com.journaldev.spring.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Configuration;

import com.journaldev.spring.service.EurekaClientService;

@Configuration
public class EurekaRegistryClient {

private EurekaClientService eurekaClientService = new EurekaClientService();
	
	@PostConstruct
    public void init() {
		eurekaClientService.registerInstance();
    }
	
	@PreDestroy
	public void destroy() {
		eurekaClientService.deRegister();
	}
	
}
