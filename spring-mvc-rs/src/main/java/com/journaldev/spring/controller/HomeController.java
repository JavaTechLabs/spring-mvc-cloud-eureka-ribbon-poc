package com.journaldev.spring.controller;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.model.User;

@RestController
public class HomeController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() throws MalformedObjectNameException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		QueryExp subQuery1 = Query.match(Query.attr("protocol"), Query.value("HTTP/1.1"));
	    QueryExp subQuery2 = Query.anySubString(Query.attr("protocol"), Query.value("Http11"));
	    QueryExp query = Query.or(subQuery1, subQuery2);
		Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), query);
		String port = null;
		for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
	        ObjectName obj = i.next();
	        port = obj.getKeyProperty("port");
	    }
		return "welcome from port " + port;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getUserName());
		return "user";
	}
}
