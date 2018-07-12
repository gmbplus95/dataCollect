package com.ifisolution.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.ifisolution.dao.EmpDAO;
	import com.ifisolution.config.CassandraOperation;
import com.ifisolution.model.Emp;
@EnableCassandraRepositories(basePackages = "com.ifi.repository")
@Repository("EmpDAO")
@Transactional
public class EmpDAOImpl implements EmpDAO{
	private static final Log LOGGER = LogFactory.getLog(EmpDAOImpl.class);
	@Autowired
	CassandraOperation cassandraOperation;
	@Override
	public List<Emp> getAllEmp() {
        Session session = cassandraOperation.getSession();
		List<Emp> result = new ArrayList<Emp>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
	        queryBuilder.append("SELECT * FROM ");
	        queryBuilder.append("test_emp");
	        queryBuilder.append(".");
	        queryBuilder.append("emp");
	        PreparedStatement ps = session.prepare(queryBuilder.toString());
	        BoundStatement bound = new BoundStatement(ps);
	        ResultSet rs = session.execute(bound);
	        rs.iterator().forEachRemaining(row ->result.add(new Emp(row.getString("emp_name"), 
	        														row.getTimestamp("date_of_birth"),
	        														row.getBool("gender"),
	        														row.getString("team_id")
					   
	        												)));
	        return result;
		}
		catch (Exception e) {
			LOGGER.error("Error get all emp: " +e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean addEmp(Emp emp) {
        Session session = cassandraOperation.getSession();
		boolean success=false;
		try {
			StringBuilder queryBuilder = new StringBuilder();
	        queryBuilder.append("INSERT INTO ");
	        queryBuilder.append("test_emp");
	        queryBuilder.append(".");
	        queryBuilder.append("emp(emp_name,date_of_birth,gender,team_id) ");
	        queryBuilder.append("VALUES");
	        queryBuilder.append(" (");
	        queryBuilder.append(" ?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(")");
	        PreparedStatement ps = session.prepare(queryBuilder.toString());
	        BoundStatement bound = new BoundStatement(ps);
	        bound.setString(0, emp.getEmp_name());
	        bound.setTimestamp(1, emp.getDate_of_birth());
	        bound.setBool(2, emp.getGender());
	        bound.setString(3, emp.getTeam_id());
	        session.execute(bound);
			success=true;
		}
		catch (Exception e) {
			LOGGER.error("Error add employee: " +e.getMessage());
		}
		return success;
	}

	@Override
	public Boolean deleteEmp(String emp_name, Date date_of_birth) {
		boolean success=false;
        Session session = cassandraOperation.getSession();
		try {
			    
			    	StringBuilder queryBuilder = new StringBuilder();
			        queryBuilder.append("DELETE FROM ");
			        queryBuilder.append("test_emp");
			        queryBuilder.append(".");
			        queryBuilder.append("emp ");
			        queryBuilder.append("WHERE");
			        queryBuilder.append(" emp_name ");
			        queryBuilder.append("= ? ");
			        queryBuilder.append("and");
			        queryBuilder.append(" date_of_birth");
			        queryBuilder.append(" =?");
			        PreparedStatement ps = session.prepare(queryBuilder.toString());
			        BoundStatement bound = new BoundStatement(ps);
			        bound.setString(0, emp_name);
			        bound.setTimestamp(1, date_of_birth);
			        session.execute(bound);
					success=true;
		}catch (Exception e) {
			LOGGER.error("Error delete employee: " +e.getMessage());
		}
		return success;
	}

	@Override
	public List<Emp> getEmpByTeamId(String teamId) {
		List<Emp> listAllEmp=this.getAllEmp();
		List<Emp> result=new ArrayList<Emp>();
		for(Emp e:listAllEmp) {
			if(e.getTeam_id().equals(teamId)) {
				result.add(e);
			}
		}
		return result;
	}

	@Override
	public List<Emp> getEmpByGender(Boolean gender) {
		List<Emp> listAllEmp=this.getAllEmp();
		List<Emp> result=new ArrayList<Emp>();
		for(Emp e:listAllEmp) {
			if(e.getGender()==gender) {
				result.add(e);
			}
		}
		return result;	}
}
