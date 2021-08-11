//package com.journaldev.spring.controller;
//
//import javax.annotation.PreDestroy;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.stereotype.Component;
//
//import com.netflix.appinfo.ApplicationInfoManager;
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.appinfo.InstanceInfo.InstanceStatus;
//import com.netflix.discovery.DefaultEurekaClientConfig;
//import com.netflix.discovery.DiscoveryManager;
//
//@Component
//public class EurekaClientManual implements InitializingBean {
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
////		DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), new DefaultEurekaClientConfig());
////
////		String vipAddress = "spring-mvc-client";
////
////		InstanceInfo nextServerInfo = null;
////		try {
////			nextServerInfo = DiscoveryManager.getInstance().getEurekaClient().getNextServerFromEureka(vipAddress,
////					false);
////			System.out.println("Found an instance of example service to talk to from eureka: "
////					+ nextServerInfo.getVIPAddress() + ":" + nextServerInfo.getPort());
////
////			System.out.println("healthCheckUrl: " + nextServerInfo.getHealthCheckUrl());
////			System.out.println("override: " + nextServerInfo.getOverriddenStatus());
////
////			System.out.println("Server Host Name " + nextServerInfo.getHostName() + " at port " + nextServerInfo.getPort());
////		} catch (Exception e) {
////			System.err.println("Cannot get an instance of example service to talk to from eureka");
////			e.printStackTrace();
////		}
//
//		DiscoveryManager.getInstance().initComponent(new MyDataCenterInstanceConfig(), new DefaultEurekaClientConfig());
//		ApplicationInfoManager.getInstance().setInstanceStatus(InstanceStatus.UP);
//		String vipAddress = "spring-mvc-client";
//		InstanceInfo nextServerInfo = null;
////		while (nextServerInfo == null) {
//			try {
//				nextServerInfo = DiscoveryManager.getInstance().getDiscoveryClient().getNextServerFromEureka(vipAddress,
//						false);
//			} catch (Throwable e) {
//				System.out.println("Waiting for service to register with eureka..");
//				e.printStackTrace();
//			}
////		}
////		System.out.println("Service started and ready to process requests..");
//
//		/*
//		 * private void main(String args[]) { final EurekaRegistryClient
//		 * sampleEurekaService = new EurekaRegistryClient();
//		 * sampleEurekaService.register(); Runtime.getRuntime().addShutdownHook(new
//		 * Thread(new Runnable() {
//		 *
//		 * @Override public void run() { //sampleEurekaService.unRegisterWithEureka();
//		 * System.out.println("Execute Hook....."); } })); try {
//		 * TimeUnit.MILLISECONDS.sleep(5000); } catch (Exception e) {
//		 * e.printStackTrace(); } }
//		 */
//
//	}
//
//	@PreDestroy
//	public void unRegister() {
//		DiscoveryManager.getInstance().shutdownComponent();
//	}
//
//}
