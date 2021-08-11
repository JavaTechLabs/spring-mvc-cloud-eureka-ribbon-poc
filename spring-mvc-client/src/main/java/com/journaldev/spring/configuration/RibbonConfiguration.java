//package com.journaldev.spring.configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.netflix.client.config.IClientConfig;
//import com.netflix.loadbalancer.BaseLoadBalancer;
//import com.netflix.loadbalancer.ILoadBalancer;
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.LoadBalancerStats;
//import com.netflix.loadbalancer.PingUrl;
//import com.netflix.loadbalancer.Server;
//import com.netflix.loadbalancer.WeightedResponseTimeRule;
//import com.netflix.ribbon.ClientOptions;
//import com.netflix.ribbon.Ribbon;
//import com.netflix.ribbon.http.HttpResourceGroup;
//
//@Configuration
//public class RibbonConfiguration {
//
////	@Bean
////	public ILoadBalancer loadBalancer(IClientConfig config) {
////		BaseLoadBalancer baseLoadBalancer = new BaseLoadBalancer("balancer", ribbonRule(),
////				new LoadBalancerStats("balancer"));
////		baseLoadBalancer.addServers(serverList());
////		return baseLoadBalancer;
////	}
////
////	public List<Server> serverList() {
////		List<Server> servers = new ArrayList<>();
////		servers.add(new Server("localhost", 8090));
////		return servers;
////	}
////
//////	@Bean
////	public PingUrl ribbonPing(IClientConfig config) {
////		return new PingUrl();
////	}
////
////	public IRule ribbonRule() {
////		return new WeightedResponseTimeRule();
////	}
//
//	@Bean
//	public HttpResourceGroup httpResourceGroup() {
//		return Ribbon.createHttpResourceGroup("movieServiceClient", ClientOptions.create()
//				.withMaxAutoRetriesNextServer(3).withConfigurationBasedServerList("localhost:8090/spring-mvc-rs"));
//	}
//
//}
