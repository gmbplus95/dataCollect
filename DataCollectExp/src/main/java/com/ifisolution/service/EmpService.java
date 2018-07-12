package com.ifisolution.service;

import java.util.Date;
import java.util.List;

import com.ifisolution.model.Emp;

public interface EmpService {
	List<Emp> getAllEmp(); //get all emp
	Boolean addEmp(Emp emp); //add a emp
	Boolean deleteEmp(String emp_name,Date date_of_birth); // delete a emp
	List<Emp> getEmpByTeamId(String teamId);//get emp by teamId
	List<Emp> getEmpByGender(Boolean gender);//get emp by gender
}
