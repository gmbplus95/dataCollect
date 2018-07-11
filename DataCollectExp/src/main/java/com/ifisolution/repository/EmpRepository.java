package com.ifisolution.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifisolution.model.Emp;

public interface EmpRepository extends CassandraRepository<Emp, String> {
	@Query("SELECT * FROM  emp")
	public List<Emp> getAllEmp();
	@Query("SELECT * FROM  emp where emp_name= :emp_name and date_of_birth= :date_of_birth")
	public Emp getEmpByNameAndDate(@Param("emp_name") String emp_name,@Param("date_of_birth") Date date_of_birth);
}
 