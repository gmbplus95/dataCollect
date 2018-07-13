package com.ifisolution.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;

public class DBConnectionFactory {
	private static Logger logger = LoggerFactory.getLogger(DBConnectionFactory.class);

	private static Cluster cluster;

	private static Session session;
	
	public static String username;

	public static String password;
	
	public static int usedHostsPerRemoteDc;
	
	public static String dc;

	public static int cassandra_port;

	public static String[] cassandra_contactpoints;
	
	public static String keyspace_test_emp;
	
	public static String keyspace_test_emp2;
	
	public static ConsistencyLevel consistencyLevelInsert;
	public static ConsistencyLevel consistencyLevelSelect;
	
	private static Session connect(String keyspace, final String[] node, final int port) {
		if (cluster == null) {
			cluster = Cluster.builder().addContactPoints(node).withLoadBalancingPolicy(
	                DCAwareRoundRobinPolicy.builder().withLocalDc(dc).withUsedHostsPerRemoteDc(usedHostsPerRemoteDc).build()
					).withCredentials(username, password).withPort(port).build();
			logger.info("Connecting to datacenter: " + dc + " on port: " + port);
		}
		return cluster.connect(keyspace);
	}

	public static synchronized Session getSession(String keyspace, final String[] node, final int port) {
		try {
			if (session == null) {
				session = connect(keyspace, node, port);
				logger.info("Connected to datacenter: " + dc + " on port: " + port + " with keyspace: " + keyspace);
			}
		} catch (Exception e) {
			logger.error("Initial SessionFactory creation failed. " + e);
			throw e;
		}
		return session;
	}
}