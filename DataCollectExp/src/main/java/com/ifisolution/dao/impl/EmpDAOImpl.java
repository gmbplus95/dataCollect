package com.ifisolution.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ifisolution.config.CassandraOperation;
import com.ifisolution.config.DBConnectionFactory;
import com.ifisolution.dao.EmpDAO;
import com.ifisolution.model.Emp;
import com.ifisolution.model.EmpSpringData;
import com.ifisolution.model.MapperEnum;
import com.ifisolution.repository.Emp1Repository;
import com.ifisolution.repository.impl.Emp1RepositoryImpl;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import ma.glasnost.orika.MapperFacade;
@Repository("EmpDAO")
@Transactional
public class EmpDAOImpl implements EmpDAO{
	private static final Log LOGGER = LogFactory.getLog(EmpDAOImpl.class);
	@Autowired
	CassandraOperation cassandraOperation;
	@Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private Emp1Repository emp1Repository;
    @Autowired
    private Emp1RepositoryImpl emp1RepositoryImpl;
	@Override
	public List<Emp> getAllEmp() {
        Session session = cassandraOperation.getSession();
		List<Emp> result = new ArrayList<Emp>();
		try {
			StringBuilder queryBuilder = new StringBuilder();
	        queryBuilder.append("SELECT * FROM ");
	        queryBuilder.append(DBConnectionFactory.keyspace_test_emp);
	        queryBuilder.append(".");
	        queryBuilder.append(DBConnectionFactory.table_emp);
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
	        queryBuilder.append(DBConnectionFactory.keyspace_test_emp);
	        queryBuilder.append(".");
	        queryBuilder.append(DBConnectionFactory.table_emp);
	        queryBuilder.append("(emp_name,date_of_birth,gender,team_id) ");
	        queryBuilder.append("VALUES");
	        queryBuilder.append(" (");
	        queryBuilder.append(" ?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(",?");
	        queryBuilder.append(");");
	        queryBuilder.append("INSERT INTO ");
	        queryBuilder.append(DBConnectionFactory.keyspace_test_emp2);
	        queryBuilder.append(".");
	        queryBuilder.append(DBConnectionFactory.table_emp);
	        queryBuilder.append("(emp_name,date_of_birth,gender,team_id) ");
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
	        bound.setString(0, emp.getEmpName());
	        bound.setTimestamp(1, emp.getDateOfBirth());	       
	        bound.set(2, emp.getGender(), Boolean.class); //set null if null
	        bound.setString(3, emp.getTeamId());
	        bound.setString(4, emp.getEmpName());
	        bound.setTimestamp(5, emp.getDateOfBirth());	       
	        bound=IsNull(emp.getGender()) ? bound.setToNull(6) :bound.setBool(6, emp.getGender()); //set null if null 2
	        bound.setString(7, emp.getTeamId());
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
			        queryBuilder.append(DBConnectionFactory.keyspace_test_emp);
			        queryBuilder.append(".");
			        queryBuilder.append(DBConnectionFactory.table_emp);
			        queryBuilder.append(" WHERE");
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
			if(e.getTeamId()!=null && e.getTeamId().equals(teamId)) {
				result.add(e);
			}
		}
		return result;
	}

	
	@Override
	public List<Emp> getAllEmp2() {
		Session session=DBConnectionFactory.getSession(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.cassandra_contactpoints, DBConnectionFactory.cassandra_port);
		List<Emp> mylist=new ArrayList<Emp>();
		try {
		Select selectQuery = QueryBuilder.select().all().from(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.table_emp);
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
				Insert insert = InsertStatement(emp,DBConnectionFactory.keyspace_test_emp);
				Insert insert2 = InsertStatement(emp,DBConnectionFactory.keyspace_test_emp2);
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
		  Delete.Where delete = DeleteStatement(emp_name, date_of_birth,DBConnectionFactory.keyspace_test_emp);
		  session.execute(delete);
		  success=true;
		}
		  catch (Exception e) {
				LOGGER.error("Error get employee: " +e.getMessage());
			}
		return success;
	}
	
	//get deleteStatement
	private Delete.Where DeleteStatement(String emp_name, Date date_of_birth,String keyspace) {
		Delete.Where delete = QueryBuilder.delete().from(DBConnectionFactory.keyspace_test_emp, DBConnectionFactory.table_emp)
			        .where(QueryBuilder.eq("emp_name", emp_name)).and(QueryBuilder.eq("date_of_birth", date_of_birth));
		return delete;
	}
	
	//get insert Emp statement
	private Insert InsertStatement(Emp emp,String keyspace) {
		Insert insertStatement = QueryBuilder.insertInto(keyspace, DBConnectionFactory.table_emp)
		        .value("emp_name", emp.getEmpName())
		        .value("date_of_birth", emp.getDateOfBirth())
		        .value("gender", emp.getGender())
		        .value("team_id", emp.getTeamId());
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
	//orika mapping
	@Override
	public List<Object> getEmpByGender(Boolean gender) {
		List<Emp> listAllEmp=this.getAllEmp2();
		List<Object> result=new ArrayList<Object>();
		for(Emp e:listAllEmp) {
			if(e.getGender()==gender) {
				Map<String,Object> src=  new HashMap<String,Object>();
//				src.put(MapperEnum.EMP_NAME.get(),e.getEmpName());
				src.put(MapperEnum.DATE_OF_BIRTH.get(),e.getDateOfBirth());
				src.put(MapperEnum.GENDER.get(), e.getGender());
				src.put(MapperEnum.TEAM.get(), e.getTeamId());
				Object a =mapperFacade.map(src, Emp.class); //mapAtoB, map src to emp class//
				result.add(a);
			}
		}     
//		listAllEmp.forEach((e) -> {
//				if(e.getGender()==gender) {
//					Map<String,Object> src=  new HashMap<String,Object>();
//					mapperFacade.map(e, src);
//					result.add(src);
//				}
//		}); //mapBtoA, map e to src, add some properties to emp object
		return result;	
		}
	

//call api jersey
	@Override
	public List<Emp> getAllEmpByCallAPI() {
		List<Emp> list=new ArrayList<Emp>();
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource("http://localhost:8080/api/employees2"); //call to get data
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			System.out.println("Failed with HTTP Error code: " + response.getStatus());
			String error= response.getEntity(String.class);
			System.out.println("Error: "+error);
			list=null;
			}
		else {
				String stringData = response.getEntity(String.class);
				if(isJSONValid(stringData)) {
					JsonParser jsonParser = new JsonParser();
					try {
							JsonElement element = jsonParser.parse(stringData);
							JsonObject jsonData=element.getAsJsonObject();
							JsonElement empData=jsonData.get("data");
							JsonArray empjsonArray= empData.getAsJsonArray();
							int size= empjsonArray.size();
							Emp emp=null;
							Gson gson = new Gson();
							for(int i=0;i<size;i++) {
									JsonElement a=empjsonArray.get(i);
									emp= gson.fromJson(a, Emp.class);
									list.add(emp);
								}
					}
					catch (Exception e) {
						LOGGER.error("Error parse employee: " +e.getMessage());
					}
				}
		}
		return list;
	}

	//orika mapping
//	private Object mapBtoA(Emp src) {
//		// init
//    	Map<String, Object> desc = new HashMap<String, Object>();
//        
//        // mapping
//    	mapperFacade.map(src, desc); //map to desc object
//    	
//    	return desc;
//	}
	//check if json
		  public boolean isJSONValid(String jsonInString) {
			   Gson gson = new Gson();
			      try {
			          gson.fromJson(jsonInString, Object.class);
			          return true;
			      } catch(com.google.gson.JsonSyntaxException ex) { 
			          return false;
			      }
		  }
		  
		//Spring Data Cassandra Repository im
		@Override
		public List<EmpSpringData> getAllEmp3() {
			return emp1Repository.getAllEmp();
			}
		@Override
		public Boolean deleteEmp3(String emp_name, Date date_of_birth) {
			EmpSpringData emp=null;
			boolean success=false;
			try {
			emp=emp1Repository.getEmpByNameAndDate(emp_name, date_of_birth);
				if(emp!=null) {
					emp1RepositoryImpl.delete(emp); //delete if emp exist
					success=true;
				}
				else success=false;
		}
			catch (Exception e) {
				LOGGER.error("Error delete employee: " +e.getMessage());
			}
			return success;
		}

		@Override
		public Boolean addOrUpdateEmp3(EmpSpringData emp) {
			EmpSpringData check=null;
			boolean success=false;
			try {
			check=emp1Repository.getEmpByNameAndDate(emp.getEmpName(), emp.getDateOfBirth());
				if(check==null) {
					EmpSpringData e=new EmpSpringData(emp.getEmpName(),emp.getDateOfBirth());
					emp1RepositoryImpl.insert(emp,e);
					LOGGER.info("insert data");
					success=true;
					return success; //insert if check =null
				}
				if(check!=null) {
					if(emp.equals(check)) {
						LOGGER.info("equal data");
						success=true;
						return success; //do nothing if check =emp
					}
					else {
						emp1RepositoryImpl.update(emp);
						LOGGER.info("update data");
						success=true;
						return success=true; //update if check != emp
					}
				}
				
			}
			
			catch (Exception e) {
				LOGGER.error("Error add or update employee: " +e.getMessage());
			}
			return success;		
			}


}
