package com.ifisolution.service;

import java.util.Date;
import java.util.List;

import com.ifisolution.model.Emp;
import com.ifisolution.model.EmpSpringData;

public interface EmpService {
	List<Emp> getAllEmp(); //get all emp
	Boolean addEmp(Emp emp); //add a emp
	Boolean deleteEmp(String emp_name,Date date_of_birth); // delete a emp
	List<Emp> getEmpByTeamId(String teamId);//get emp by teamId
	List<Object> getEmpByGender(Boolean gender);//get emp by gender
	List<Emp> getAllEmp2(); //get all emp 2
	Boolean addEmp2(Emp emp); //add a emp 2
	Boolean deleteEmp2(String emp_name,Date date_of_birth); // delete a emp 2
	List<EmpSpringData> getAllEmp3(); //get all emp
	Boolean addOrUpdateEmp3(EmpSpringData emp); //add a emp 3
	Boolean deleteEmp3(String emp_name,Date date_of_birth); // delete a emp
	List<Emp> getAllEmpByCallAPI();

}
