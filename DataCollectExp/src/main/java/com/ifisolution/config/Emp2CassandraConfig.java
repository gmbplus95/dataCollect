package com.ifisolution.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = "com.ifisolution.repository",cassandraTemplateRef = "emp1CassandraTemplate")
public class Emp2CassandraConfig extends AppConfig {
	@Value("${cassandra.keyspace.test_emp2}")
	private String keyspace_test_emp2;

	/**
     * Get the keyspace name for measurement. 
     * 
     * @return the keyspace name for measurement
     */
	@Override
	public String getKeyspaceName() {
		return keyspace_test_emp2;
	}
	
	/**
     * Creates a new {@link CassandraSessionFactoryBean} for measurement. 
     * 
     * @return the session bean
     */
	@Override
	@Bean(name = "emp2CassandraSesion")
	public CassandraSessionFactoryBean session() {
		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster().getObject());
		session.setKeyspaceName(getKeyspaceName());
		session.setConverter(cassandraConverter());
		session.setSchemaAction(SchemaAction.NONE);

		return session;
	}
	
	/**
     * Creates a new {@link CassandraAdminTemplate} for measurement. 
     * 
     * @return the cassandra template bean
     */
	@Override
	@Primary
	@Bean(name = "emp2CassandraTemplate")
	public CassandraAdminOperations cassandraTemplate() throws Exception {
		return new CassandraAdminTemplate(session().getObject(), cassandraConverter());
	}
}
