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
	    private String empName;
		@ClusterKey
		@CassandraColumn(name ="date_of_birth")
	  	Date dateOfBirth;
		@CassandraColumn(name ="gender")
	    private Boolean gender;
		@CassandraColumn(name ="team_id")
	    private String teamId;
		
		public Emp(String empName, Date dateOfBirth, Boolean gender, String teamId) {
			super();
			this.empName = empName;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.teamId = teamId;
		}

		public Emp() {
			super();
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public Boolean getGender() {
			return gender;
		}

		public void setGender(Boolean gender) {
			this.gender = gender;
		}

		public String getTeamId() {
			return teamId;
		}

		public void setTeamId(String teamId) {
			this.teamId = teamId;
		}
		
		//getter and setter
		
		
	}
