package com.ifisolution.model;

import java.util.Date;

import com.ifisolution.util.CassandraColumn;
import com.ifisolution.util.CassandraTable;
import com.ifisolution.util.ClusterKey;
import com.ifisolution.util.PartitionKey;

@CassandraTable(name ="emp")
public class Emp {
		@PartitionKey
		@CassandraColumn(name ="emp_name")
	    private String emp_name;
		@ClusterKey
		@CassandraColumn(name ="date_of_birth")
	  	Date date_of_birth;
		@CassandraColumn(name ="gender")
	    private Boolean gender;
		@CassandraColumn(name ="team_id")
	    private String team_id;
		
		//getter and setter
		public String getEmp_name() {
			return emp_name;
		}
		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}
		public Date getDate_of_birth() {
			return date_of_birth;
		}
		public void setDate_of_birth(Date date_of_birth) {
			this.date_of_birth = date_of_birth;
		}
		public Boolean getGender() {
			return gender;
		}
		public void setGender(Boolean gender) {
			this.gender = gender;
		}
		public String getTeam_id() {
			return team_id;
		}
		public void setTeam_id(String team_id) {
			this.team_id = team_id;
		}
		
		//contructor
		public Emp() {
			super();
		}
		public Emp(String emp_name, Date date_of_birth, Boolean gender, String team_id) {
			super();
			this.emp_name = emp_name;
			this.date_of_birth = date_of_birth;
			this.gender = gender;
			this.team_id = team_id;
		}
	    
	    
	}
