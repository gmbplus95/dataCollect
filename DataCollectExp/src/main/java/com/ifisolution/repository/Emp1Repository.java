package com.ifisolution.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifisolution.model.EmpSpringData;

@Repository
public interface Emp1Repository  extends CassandraRepository<EmpSpringData,String> {
	@Query("SELECT * FROM test_emp.emp")
    List<EmpSpringData> getAllEmp();
	@Query("SELECT * FROM test_emp.emp where emp_name=:empName and date_of_birth=:dateOfBirth")
	EmpSpringData getEmpByNameAndDate(@Param("empName") String empName,@Param("dateOfBirth")Date dateOfBirth);
}
