package com.journaldev.spring.configuration;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="notification-service")
public interface AppConfig {

}
