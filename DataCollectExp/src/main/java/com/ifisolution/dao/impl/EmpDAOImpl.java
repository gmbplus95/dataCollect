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

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.ifisolution.dao.EmpDAO;
	import com.ifisolution.config.CassandraOperation;
import com.ifisolution.config.DBConnectionFactory;
import com.ifisolution.model.Emp;
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
	        rs.iterator().forEachRemaining(row ->result.add(RowToObj(row)));
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
			queryBuilder.append("BEGIN BATCH ");
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
	        queryBuilder.append(");");
	        queryBuilder.append("INSERT INTO ");
	        queryBuilder.append("test_emp2");
	        queryBuilder.append(".");
	        queryBuilder.append("emp(emp_name,date_of_birth,gender,team_id) ");
	        queryBuilder.append("VALUES");
	        queryBuilder.append(" (");
	        queryBuilder.append(" ?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(");");
	        queryBuilder.append(" APPLY BATCH;");
	        PreparedStatement ps = session.prepare(queryBuilder.toString());
	        BoundStatement bound = new BoundStatement(ps);
	        bound.setString(0, emp.getEmp_name());
	        bound.setTimestamp(1, emp.getDate_of_birth());	       
	        bound=IsNull(emp.getGender()) ? bound.setToNull(2) :bound.setBool(2, emp.getGender());
	        bound.setString(3, emp.getTeam_id());
	        bound.setString(4, emp.getEmp_name());
	        bound.setTimestamp(5, emp.getDate_of_birth());	       
	        bound=IsNull(emp.getGender()) ? bound.setToNull(6) :bound.setBool(6, emp.getGender());
	        bound.setString(7, emp.getTeam_id());
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
		List<Emp> listAllEmp=this.getAllEmp2();
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
		List<Emp> listAllEmp=this.getAllEmp2();
		List<Emp> result=new ArrayList<Emp>();
		for(Emp e:listAllEmp) {
			if(e.getGender()==gender) {
				result.add(e);
			}
		}
		return result;	}
	
	@Override
	public List<Emp> getAllEmp2() {
		Session session=DBConnectionFactory.getSession(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.cassandra_contactpoints, DBConnectionFactory.cassandra_port);
		List<Emp> mylist=new ArrayList<Emp>();
		try {
		Select selectQuery = QueryBuilder.select().all().from(DBConnectionFactory.keyspace_test_emp, "emp");
		ResultSet results = session.execute(selectQuery);
		results.iterator().forEachRemaining(row ->mylist.add(RowToObj(row)));
//			for(Row row:results) {
//				Emp e = RowToObj(row);
//				mylist.add(e);
//			}
		}
		catch (Exception e) {
			LOGGER.error("Error get employee: " +e.getMessage());
		}
		return mylist;
	}
	

	@Override
	public Boolean addEmp2(Emp emp) {
		boolean success=false;
		try {
				//just need one session to apply batch 2 keyspace
				Session session=DBConnectionFactory.getSession(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.cassandra_contactpoints, DBConnectionFactory.cassandra_port);
				Insert insert = InsertEmp(emp,DBConnectionFactory.keyspace_test_emp);
				Insert insert2 = InsertEmp(emp,DBConnectionFactory.keyspace_test_emp2);
				BatchStatement batch = new BatchStatement();
				batch.add(insert);
				batch.add(insert2);
				session.execute(batch);
				success=true;
		}
		catch (Exception e) {
			LOGGER.error("Error get employee: " +e.getMessage());
		}
		return success;
	}
	
	@Override
	public Boolean deleteEmp2(String emp_name, Date date_of_birth) {
		boolean success=false;
		try {
		  Session session=DBConnectionFactory.getSession(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.cassandra_contactpoints, DBConnectionFactory.cassandra_port);
		  Delete.Where delete = getDeleteStatement(emp_name, date_of_birth,DBConnectionFactory.keyspace_test_emp);
		  session.execute(delete);
		  success=true;
		}
		  catch (Exception e) {
				LOGGER.error("Error get employee: " +e.getMessage());
			}
		return success;
	}
	
	//get deleteStatement
	private Delete.Where getDeleteStatement(String emp_name, Date date_of_birth,String keyspace) {
		Delete.Where delete = QueryBuilder.delete().from(DBConnectionFactory.keyspace_test_emp, "emp")
			        .where(QueryBuilder.eq("emp_name", emp_name)).and(QueryBuilder.eq("date_of_birth", date_of_birth));
		return delete;
	}
	
	//get insert Emp statement
	private Insert InsertEmp(Emp emp,String keyspace) {
		Insert insertStatement = QueryBuilder.insertInto(keyspace, "emp")
		        .value("emp_name", emp.getEmp_name())
		        .value("date_of_birth", emp.getDate_of_birth())
		        .value("gender", emp.getGender())
		        .value("team_id", emp.getTeam_id());
		return insertStatement;
	}

	//change row to object
	private Emp RowToObj(Row row) {
		Emp e=new Emp(row.getString("emp_name"),
					  row.getTimestamp("date_of_birth"),
					  row.isNull("gender")?null:row.getBool("gender"),
					  row.getString("team_id"));
		return e;
	}
	
	
	//check isNull
	public boolean IsNull(Boolean a) {
		if(a==null) {
			return true;
		}
		else return false;
	}

	

}
