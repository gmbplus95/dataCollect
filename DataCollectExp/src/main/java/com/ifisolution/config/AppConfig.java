package com.ifisolution.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = "com.ifisolution.repository")
public class AppConfig extends AbstractCassandraConfiguration {

	private static final Log LOGGER = LogFactory.getLog(AppConfig.class);

    @Autowired
    private Environment environment;

    @Override
    protected String getKeyspaceName() {
        return environment.getProperty("cassandra.keyspace");
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
        LOGGER.info("Cluster created with contact points [" + environment.getProperty("cassandra.contactpoints") + "] " + "& port [" + Integer.parseInt(environment.getProperty("cassandra.port")) + "].");
        return cluster;
    }
    
    @Override
	public CassandraSessionFactoryBean session() {
		return super.session();
	}

	@Override
	public SessionFactory sessionFactory() {
		return super.sessionFactory();
	}


}