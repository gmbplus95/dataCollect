package com.ifisolution;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.ifisolution.config.ConnectionFactory;
import com.ifisolution.config.DBConnectionFactory;
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
	
	@Value("${cassandra.keyspace.test_emp}")
	private String keyspace_test_emp;

	@Value("${cassandra.keyspace.test_emp2}")
	private String keyspace_test_emp2;
	
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
		//solution 1
//		ConnectionFactory.contactPoints = this.contactPoints;
//		ConnectionFactory.dataCenter = this.dataCenter;
//		ConnectionFactory.usedHostsPerRemoteDc = this.usedHostsPerRemoteDc;
//		ConnectionFactory.port = this.port;
//		ConnectionFactory.username = this.username;
//		ConnectionFactory.password = this.password;
//		ConnectionFactory.fetchSize = this.fetchSize;
//		ConnectionFactory.consistencyLevel = this.consistencyLevel;
//		
		//solution 2
		DBConnectionFactory.cassandra_contactpoints =this.contactPoints;
		DBConnectionFactory.cassandra_port = this.port;
		DBConnectionFactory.keyspace_test_emp = this.keyspace_test_emp;
		DBConnectionFactory.keyspace_test_emp2 = this.keyspace_test_emp2;
		DBConnectionFactory.username = username;
		DBConnectionFactory.password = password;
		DBConnectionFactory.dc = this.dataCenter;
		DBConnectionFactory.usedHostsPerRemoteDc = this.usedHostsPerRemoteDc;
	}
}
