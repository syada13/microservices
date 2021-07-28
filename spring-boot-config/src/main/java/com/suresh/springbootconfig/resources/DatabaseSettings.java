package com.suresh.springbootconfig.resources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
public class DatabaseSettings {
	
	private String connections;
	private String host;
	private int port;


	public String getConnections() {
		return connections;
	}
	
	public void setConnections(String connections) {
		this.connections = connections;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	

}
