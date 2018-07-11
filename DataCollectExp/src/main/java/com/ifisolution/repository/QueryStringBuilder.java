package com.ifisolution.repository;

import com.ifisolution.model.Emp;

public class QueryStringBuilder {
	public String getAllEmp;
	public String addEmp;
	public String getAddEmp() {
		return addEmp;
	}

	public void setAddEmp(String addEmp) {
		this.addEmp = addEmp;
	}

	public QueryStringBuilder() {
		super();
		StringBuilder getAllEmpsb=new StringBuilder();
		getAllEmpsb.append("SELECT * FROM emp");
		this.getAllEmp=getAllEmpsb.toString();
	}
	
	public QueryStringBuilder(Emp emp) {
		StringBuilder addEmpsb=new StringBuilder();
		addEmpsb.append("INSERT INTO emp(emp_name,date_of_birth,gender,team_id) VALUES (");
		addEmpsb.append(emp.getEmp_name());
		addEmpsb.append(",");
		addEmpsb.append(emp.getDate_of_birth());
		addEmpsb.append(",");
		addEmpsb.append(emp.getGender());
		addEmpsb.append(",");
		addEmpsb.append(emp.getTeam_id());
		addEmpsb.append(")");
		this.addEmp=addEmpsb.toString();
	}

	public String getGetAllEmp() {
		return getAllEmp;
	}

	public void setGetAllEmp(String getAllEmp) {
		this.getAllEmp = getAllEmp;
	}
	
}
