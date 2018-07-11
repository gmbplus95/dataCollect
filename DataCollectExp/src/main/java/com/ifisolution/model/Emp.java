package com.ifisolution.model;

import java.util.Date;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("emp")
public class Emp {
	    @PrimaryKeyColumn(
	      name = "emp_name", 
	      ordinal = 0, 
	      type = PrimaryKeyType.PARTITIONED)
	    private String emp_name;
	    @PrimaryKeyColumn(
	  	      name = "date_of_birth", 
	  	      ordinal = 1, 
	  	      type = PrimaryKeyType.CLUSTERED,
	  	      ordering = Ordering.ASCENDING)
	  	    Date date_of_birth;
	    @Column
	    private Boolean gender;
	    @Column
	    private String team_id;
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
