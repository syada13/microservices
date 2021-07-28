package com.suresh.springbootconfig.resources;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Value("${my.greeting: Hello Suresh}")
	private String greetingMessage;
	
	@Value("${app.description}")
	private String greetingDescription;
	
	@Value("${my.counting.list.values}")
	private List<String> countValues;
	
	@Value("#{${db.values}}")
	private Map<String,String> dbValues;
	
	@Autowired
	private DatabaseSettings databaseSettings;
	
	public GreetingController() {
		
	}
	
	@GetMapping("/greeting")
	public String greeting() {
		
		//return greetingMessage +  greetingDescription + countValues + dbValues;
		return databaseSettings.getConnections() + " " + databaseSettings.getHost() + " " + databaseSettings.getPort();
		
	}
	
	

}
