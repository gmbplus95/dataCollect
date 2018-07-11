package com.ifisolution.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifisolution.dao.EmpDAO;
import com.ifisolution.model.Emp;
import com.ifisolution.repository.EmpRepository;
@EnableCassandraRepositories(basePackages = "com.ifi.repository")
@Repository("EmpDAO")
@Transactional
public class EmpDAOImpl implements EmpDAO{
	private static final Log LOGGER = LogFactory.getLog(EmpDAOImpl.class);
	@Autowired
	EmpRepository empRepository;
	@Override
	public List<Emp> getAllEmp() {
		List<Emp> result =null;
		try {
			result=empRepository.getAllEmp();
		}
		catch (Exception e) {
			LOGGER.error("Error get all emp: " +e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean addEmp(Emp emp) {
		boolean success=false;
		try {
			empRepository.save(emp);
			success=true;
		}
		catch (Exception e) {
			LOGGER.error("Error add employee: " +e.getMessage());
		}
		return success;
	}

	@Override
	public Boolean updateEmp(Emp emp) {
		boolean success=false;
		try {
				empRepository.save(emp);
				success=true;
		}catch (Exception e) {
			LOGGER.error("Error update employee: " +e.getMessage());
		}
		return success;
	}

	@Override
	public Boolean deleteEmp(String emp_name, Date date_of_birth) {
		boolean success=false;
		try {
			    Emp deletedEmp=empRepository.getEmpByNameAndDate(emp_name, date_of_birth);
			    if(deletedEmp != null) {
			    	empRepository.delete(deletedEmp);
			    	success=true;
			    }
			    else success=false;
		}catch (Exception e) {
			LOGGER.error("Error delete employee: " +e.getMessage());
		}
		return success;
	}
}
