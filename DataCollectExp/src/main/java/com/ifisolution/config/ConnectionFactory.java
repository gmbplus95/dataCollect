package com.ifisolution.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;

public class ConnectionFactory {

	private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

	private static Cluster cluster;

	// private static Map<String, Session> sessionMap = new HashMap<>();

	private static Session session = null;

	public static String consistencyLevel;

	public static int fetchSize;

	public static String[] contactPoints;

	public static String dataCenter;

	public static int usedHostsPerRemoteDc;

	public static int port;

	public static String username;

	public static String password;

	private static Session connect(final String localDataCentre, final String[] node, final int usedHostPerRemote, final int port) {
		if (cluster == null) {
			logger.debug("Connecting to datacenter: " + Arrays.asList(node) + " on port: " + port + " with username: " + username + "; password = " + password);
			if ((username != null && !username.isEmpty()) && (password != null && !password.isEmpty())) {
				cluster = Cluster.builder().addContactPoints(node)
						.withLoadBalancingPolicy(DCAwareRoundRobinPolicy.builder().withLocalDc(dataCenter).withUsedHostsPerRemoteDc(usedHostsPerRemoteDc).build()).withCredentials(username, password)
						.withPort(port).build();
				configSession(cluster);

			} else {
				cluster = Cluster.builder().addContactPoints(node).withPort(port).build();
				configSession(cluster);
			}
		}
		return cluster.connect();
	}

	public static Session getSession(final String localDataCentre, final String[] node, final int usedHostPerRemote, final int port) {

		try {
			if (session == null) {
				session = connect(localDataCentre, node, usedHostPerRemote, port);
				logger.info("Connected to datacenter: " + localDataCentre + " with node" + Arrays.asList(node) + " on port: " + port + " and host remote:" + usedHostPerRemote);
			}
		} catch (Exception ex) {
			logger.error("ERROR: Get session", ex);
			throw ex;

		}

		return session;
	}

	public static void configSession(Cluster cluster) {
		cluster.getConfiguration().getSocketOptions().setConnectTimeoutMillis(300000);
		cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(300000);
		cluster.getConfiguration().getQueryOptions().setFetchSize(fetchSize);

		cluster.getConfiguration().getPoolingOptions().setConnectionsPerHost(HostDistance.LOCAL, 100, 400);
		cluster.getConfiguration().getPoolingOptions().setConnectionsPerHost(HostDistance.REMOTE, 100, 400);
		cluster.getConfiguration().getPoolingOptions().setMaxRequestsPerConnection(HostDistance.LOCAL, 32768).setMaxRequestsPerConnection(HostDistance.REMOTE, 32768);
		ConsistencyLevel cl = ConsistencyLevel.valueOf(consistencyLevel);
		cluster.getConfiguration().getQueryOptions().setConsistencyLevel(cl);
	}
}
