package com.journaldev.spring.service;

import java.util.Properties;

import com.journaldev.spring.configuration.ConfigurationUtil;
import com.journaldev.spring.configuration.CustomEurekaClientConfig;
import com.journaldev.spring.configuration.WebAppInstanceConfig;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;

public class EurekaClientService {

	private CustomEurekaClientConfig eurekaClientConfig;
	private WebAppInstanceConfig webAppInstanceConfig;
	private EurekaClient eurekaClient;
	private Properties properties;
	private static final String CONFIG_NAME = "eureka-client";

	public EurekaClientService() {
		properties = ConfigurationUtil.loadCascadedProperties(CONFIG_NAME);
		this.webAppInstanceConfig = new WebAppInstanceConfig(properties);
		this.eurekaClientConfig = new CustomEurekaClientConfig();
	}

	protected ApplicationInfoManager initializeApplicationInfoManager(EurekaInstanceConfig instanceConfig) {
		InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();
		return new ApplicationInfoManager(instanceConfig, instanceInfo);
	}

	protected void initializeEurekaClient(ApplicationInfoManager applicationInfoManager,
			EurekaClientConfig clientConfig) {
		eurekaClient = new DiscoveryClient(applicationInfoManager, clientConfig);
	}

	/**
	 * this is the entry point for registering the app with the Eureka Server
	 */
	public void registerInstance() {
		System.out.println("registering this app with eureka server");
		ApplicationInfoManager applicationInfoManager = initializeApplicationInfoManager(webAppInstanceConfig);
		initializeEurekaClient(applicationInfoManager, eurekaClientConfig);
		applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);
	}

	public void deRegister() {
		System.out.println("shutting down eureka client and de-registering this instance from the server");
		if (eurekaClient != null)
			eurekaClient.shutdown();
	}

	/**
	 * this should be called after calling the registerInstance() method which will
	 * instantiate the client. Otherwise, it may return NULL
	 * 
	 * @return eurekaClient
	 */
	public EurekaClient getEurekaClient() {
		return eurekaClient;
	}

	/**
	 * this will return the homepage URL of a remote service that's connected to the
	 * Eureka Server. This can be useful for making requests to the remote service
	 * without having to hard-code it's URL - which is volatile in nature If the
	 * service is not found or an exception occurred in the process it will return
	 * null
	 * 
	 * @param serviceName the vipAddress of the remote service
	 * @return the homepage URL of the remote service or null if not found
	 */
	public String getRemoteServiceHomepageURL(String serviceName) {

		InstanceInfo nextServerInfo = null;

		try {
			nextServerInfo = eurekaClient.getNextServerFromEureka(serviceName, false);
		} catch (Exception e) {
			System.out.println("Cannot get any instance of {} from eureka: " + e.getMessage());
			return null;
		}

		return nextServerInfo.getHomePageUrl();
	}

}
