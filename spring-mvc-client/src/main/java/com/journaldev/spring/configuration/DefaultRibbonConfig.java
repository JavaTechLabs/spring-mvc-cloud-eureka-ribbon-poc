package com.journaldev.spring.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.ServerListSubsetFilter;

@Configuration
public class DefaultRibbonConfig {

//	@Autowired(required = false)
//	private EurekaClientConfig clientConfig;
//	
	@Value("${ribbon.eureka.approximateZoneFromHostname:false}")
	private boolean approximateZoneFromHostname = false;
	
	protected static final String DEFAULT_NAMESPACE = "ribbon";

	
//	@Autowired
//	private EurekaInstanceConfigBean eurekaConfig;
	
	protected static final String VALUE_NOT_SET = "__not__set__";
	

//	@Value("${ribbon.client.name}")
//	private String serviceId = "client";

	public static class BazServiceList extends ConfigurationBasedServerList {

		public BazServiceList(IClientConfig config) {
			super.initWithNiwsConfig(config);
		}

	}

	@PostConstruct
	public void test() {
		System.out.println("DefaultRibbonConfig Config loaded");
	}

	@Bean
	public IRule ribbonRule() {
		return new BestAvailableRule();
	}
	
	@Bean
	public IPing ribbonPing() {
		return new PingUrl();
	}
	
//	@Bean
//	public ServerList<Server> ribbonServerList(IClientConfig config) {
//		return new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
//	}

	@Bean
	public ServerListSubsetFilter serverListFilter() {
		ServerListSubsetFilter filter = new ServerListSubsetFilter();
		return filter;
	}

	
//	@Bean
//    IClientConfig iClientConfig() {
//        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
//        config.setClientName("notification-service");
//        return config;
//    }

//	@Bean
//	public ServerList ribbonServerList(IClientConfig config) {
//		DiscoveryEnabledNIWSServerList discoveryServerList = new DiscoveryEnabledNIWSServerList(config);
//		DomainExtractingServerList serverList = new DomainExtractingServerList(discoveryServerList, config, false);
//		return serverList;
//	}
//
//	@PostConstruct
//	public void preprocess() {
//		String zone = ConfigurationManager.getDeploymentContext().getValue(ContextKey.zone);
//		if (this.clientConfig != null && StringUtils.isEmpty(zone)) {
//			if (approximateZoneFromHostname) {
//				String approxZone = ZoneUtils.extractApproximateZone(eurekaConfig.getHostname());
//				System.out.println("Setting Zone To " + approxZone);
//				ConfigurationManager.getDeploymentContext().setValue(ContextKey.zone, approxZone);
//			} else {
//				String[] zones = this.clientConfig.getAvailabilityZones(this.clientConfig.getRegion());
//				String availabilityZone = zones != null && zones.length > 0 ? zones[0] : null;
//				if (availabilityZone != null) {
//					// You can set this with archaius.deployment.* (maybe requires
//					// custom deployment context)?
//					ConfigurationManager.getDeploymentContext().setValue(ContextKey.zone, availabilityZone);
//				}
//			}
//		}
////		setProp(this.serviceId, DeploymentContextBasedVipAddresses.key(), this.serviceId);
////		setProp(this.serviceId, EnableZoneAffinity.key(), "true");
//	}
//
//	protected void setProp(String serviceId, String suffix, String value) {
//		// how to set the namespace properly?
//		String key = getKey(serviceId, suffix);
//		DynamicStringProperty property = getProperty(key);
//		if (property.get().equals(VALUE_NOT_SET)) {
//			ConfigurationManager.getConfigInstance().setProperty(key, value);
//		}
//	}
//
//	protected DynamicStringProperty getProperty(String key) {
//		return DynamicPropertyFactory.getInstance().getStringProperty(key, VALUE_NOT_SET);
//	}
//
//	protected String getKey(String serviceId, String suffix) {
//		return serviceId + "." + DEFAULT_NAMESPACE + "." + suffix;
//	}

}
