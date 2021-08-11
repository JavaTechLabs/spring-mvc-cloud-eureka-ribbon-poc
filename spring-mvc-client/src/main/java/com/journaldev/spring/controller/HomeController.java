package com.journaldev.spring.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.ClientException;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

@RestController
public class HomeController {

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private RestClient client;

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws URISyntaxException 
	 * @throws ClientException 
	 */
	@GetMapping
	public String home(Locale locale, Model model) throws URISyntaxException, ClientException {
		ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
//		System.out.println("stats: " + lb.getLoadBalancerStats());
		
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("/spring-mvc-rs/welcome")).build();	
		HttpResponse responseWithLoadBalancer = client.executeWithLoadBalancer(request);
		
		
//		HttpClientRequest request = HttpClientRequest.newBuilder().setUri(new URI("http://server/spring-mvc-rs/welcome")).build();
//		String result = restTemplate.getForObject("http://server/spring-mvc-rs/welcome", String.class);
		System.out.println("Request by LB = " + responseWithLoadBalancer.getRequestedURI() + " , Response: " + responseWithLoadBalancer.getStatus());
		return "welcome";
	}
}
