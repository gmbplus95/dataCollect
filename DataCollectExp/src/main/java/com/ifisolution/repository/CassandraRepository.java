package com.ifisolution.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CassandraRepository {
	public void insert(Object object1,Object object2);
	public void update(Object object);
	public void delete(Object object);
}
