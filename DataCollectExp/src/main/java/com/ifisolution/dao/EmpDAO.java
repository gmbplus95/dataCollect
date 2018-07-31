package com.ifisolution.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ifisolution.model.Emp;
import com.ifisolution.model.EmpSpringData;
@Repository
public interface EmpDAO {
		List<Emp> getAllEmp(); //get all emp
		Boolean addEmp(Emp emp); //add a emp
		Boolean deleteEmp(String emp_name,Date date_of_birth); // delete a emp
		List<Emp> getEmpByTeamId(String teamId);
		List<Object> getEmpByGender(Boolean gender);
		List<Emp> getAllEmp2(); //get all emp solution 2
		Boolean addEmp2(Emp emp);//add a emp solution 2
		Boolean deleteEmp2(String emp_name,Date date_of_birth); //delete emp solution 2
		List<EmpSpringData> getAllEmp3(); //get all emp solution3
		Boolean addOrUpdateEmp3(EmpSpringData emp);//add a emp solution 3
		Boolean deleteEmp3(String emp_name,Date date_of_birth); // delete a emp solution 3
		List<Emp> getAllEmpByCallAPI(); //get all emp by call api
}

