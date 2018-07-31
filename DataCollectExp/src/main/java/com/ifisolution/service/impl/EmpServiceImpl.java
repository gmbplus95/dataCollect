package com.ifisolution.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifisolution.dao.EmpDAO;
import com.ifisolution.model.Emp;
import com.ifisolution.model.EmpSpringData;
import com.ifisolution.service.EmpService;
@Service("EmpService")
public class EmpServiceImpl implements EmpService{
	@Autowired
	EmpDAO empDAO;
	@Override
	public List<Emp> getAllEmp() {
		return empDAO.getAllEmp();
	}

	@Override
	public Boolean addEmp(Emp emp) {
		if (empDAO.addEmp(emp)==true)
		return 	true;
		else return false;
	}


	@Override
	public Boolean deleteEmp(String emp_name, Date date_of_birth) {
		if(empDAO.deleteEmp(emp_name,date_of_birth)==true)
			return true;
			else return false;		
	}

	@Override
	public List<Emp> getEmpByTeamId(String teamId) {
		return empDAO.getEmpByTeamId(teamId);
	}

	@Override
	public List<Object> getEmpByGender(Boolean gender) {
		return empDAO.getEmpByGender(gender);
	}

	@Override
	public List<Emp> getAllEmp2() {
		return empDAO.getAllEmp2();
	}

	@Override
	public Boolean addEmp2(Emp emp) {
		if (empDAO.addEmp2(emp)==true)
			return 	true;
			else return false;
	}

	@Override
	public Boolean deleteEmp2(String emp_name, Date date_of_birth) {
		if(empDAO.deleteEmp2(emp_name,date_of_birth)==true)
			return true;
			else return false;		
	}

	@Override
	public List<EmpSpringData> getAllEmp3() {
		return empDAO.getAllEmp3();
	}

	@Override
	public Boolean addOrUpdateEmp3(EmpSpringData emp) {
		if (empDAO.addOrUpdateEmp3(emp)==true)
			return 	true;
			else return false;
	}

	@Override
	public List<Emp> getAllEmpByCallAPI() {
		return empDAO.getAllEmpByCallAPI();
	}

	@Override
	public Boolean deleteEmp3(String emp_name, Date date_of_birth) {
		if(empDAO.deleteEmp3(emp_name,date_of_birth)==true)
			return true;
			else return false;
		}

}
