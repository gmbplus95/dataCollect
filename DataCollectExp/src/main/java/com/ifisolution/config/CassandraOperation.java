package com.ifisolution.config;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

@Component
public class CassandraOperation {
	
	public static Map<String, PreparedStatement> statementCache = new HashMap<>();

	public Session getSession() {
		return ConnectionFactory.getSession(ConnectionFactory.dataCenter, ConnectionFactory.contactPoints, ConnectionFactory.usedHostsPerRemoteDc, ConnectionFactory.port);
	}

	public static Session getSession(final String[] node, final int port) {
		// return ConnectionFactory.getSession(keyspace, node, port);
		return ConnectionFactory.getSession(ConnectionFactory.dataCenter, node, ConnectionFactory.usedHostsPerRemoteDc, port);
	}
	
    public PreparedStatement getStatement(String cql) {
        PreparedStatement ps = statementCache.get(cql);
        // no statement cached, create one and cache it now.
        if (ps == null) {
        	Session session = getSession();
            ps = session.prepare(cql);
            statementCache.put(cql, ps);
        }
        return ps;
    }
}
