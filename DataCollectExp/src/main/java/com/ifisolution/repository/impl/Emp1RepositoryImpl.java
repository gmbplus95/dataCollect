package com.ifisolution.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.ifisolution.repository.CassandraRepository;

@Repository
public class Emp1RepositoryImpl implements CassandraRepository {
	 @Autowired
	 @Qualifier("emp1CassandraTemplate")
	 private CassandraOperations emp1CassandraTemplate;
	 @Autowired
	 @Qualifier("emp2CassandraTemplate")
	 private CassandraOperations emp2CassandraTemplate;

	@Override
	public void insert(Object object1,Object object2) {
		 emp1CassandraTemplate.insert(object1);
		 emp2CassandraTemplate.insert(object2);
	}

	@Override
	public void update(Object object) {
		 emp1CassandraTemplate.update(object);
	}

	@Override
	public void delete(Object object) {
		emp1CassandraTemplate.delete(object);
	}
}
