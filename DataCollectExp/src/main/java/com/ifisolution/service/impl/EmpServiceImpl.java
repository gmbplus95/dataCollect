package com.ifisolution.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifisolution.dao.EmpDAO;
import com.ifisolution.model.Emp;
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

//	@Override
//	public Boolean updateEmp(Emp emp) {
//		if(empDAO.updateEmp(emp)==true)
//			return true;
//			else return false;		
//		
//	}

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
	public List<Emp> getEmpByGender(Boolean gender) {
		return empDAO.getEmpByGender(gender);
	}

}
