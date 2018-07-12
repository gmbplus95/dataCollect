package com.ifisolution;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.ifisolution.config.ConnectionFactory;
@PropertySource(value = {"classpath:cassandra.properties"})
@SpringBootApplication
public class DataCollectExpApplication {
	@Value("${cassandra.contactpoints}")
	private String[] contactPoints;

	@Value("${cassandra.port}")
	private int port;

	@Value("${cassandra.datacenter}")
	private String dataCenter;

	@Value("${cassandra.usedHostsPerRemoteDc}")
	private int usedHostsPerRemoteDc;

	@Value("${cassandra.username}")
	private String username;

	@Value("${cassandra.password}")
	private String password;

	@Value("${cassandra.query.fetchSize}")
	private int fetchSize;

	@Value("${cassandra.consistencyLevel}")
	private String consistencyLevel;
	public static void main(String[] args) {
		SpringApplication.run(DataCollectExpApplication.class, args);
	}
	
	@PostConstruct
	public void postConstruct() {
		ConnectionFactory.contactPoints = this.contactPoints;
		ConnectionFactory.dataCenter = this.dataCenter;
		ConnectionFactory.usedHostsPerRemoteDc = this.usedHostsPerRemoteDc;
		ConnectionFactory.port = this.port;
		ConnectionFactory.username = this.username;
		ConnectionFactory.password = this.password;
		ConnectionFactory.fetchSize = this.fetchSize;
		ConnectionFactory.consistencyLevel = this.consistencyLevel;
	}
}
