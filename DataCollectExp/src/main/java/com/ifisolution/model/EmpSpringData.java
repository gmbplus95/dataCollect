package com.ifisolution.model;

import java.util.Date;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("emp")
public class EmpSpringData {
	@PrimaryKeyColumn(
		      name = "emp_name", 
		      ordinal = 0, 
		      type = PrimaryKeyType.PARTITIONED)
		    private String empName;
		    @PrimaryKeyColumn(
		    	      name = "date_of_birth", 
		    	      ordinal = 1, 
		    	      type = PrimaryKeyType.CLUSTERED, 
		    	      ordering = Ordering.ASCENDING)
		    private Date dateOfBirth;
		    @Column(value="gender")
		    private Boolean gender;
		    @Column(value="team_id")
		    private String teamId;
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
		
			public EmpSpringData(String empName, Date dateOfBirth) {
				super();
				this.empName = empName;
				this.dateOfBirth = dateOfBirth;
			}
			public EmpSpringData(String empName, Date dateOfBirth, Boolean gender, String team_id) {
				super();
				this.empName = empName;
				this.dateOfBirth = dateOfBirth;
				this.gender = gender;
				this.teamId = team_id;
			}
			
			public String getTeamId() {
				return teamId;
			}
			public void setTeamId(String teamId) {
				this.teamId = teamId;
			}
			public EmpSpringData() {
				super();
			}
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
				result = prime * result + ((empName == null) ? 0 : empName.hashCode());
				result = prime * result + ((gender == null) ? 0 : gender.hashCode());
				result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
				return result;
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				EmpSpringData other = (EmpSpringData) obj;
				if (dateOfBirth == null) {
					if (other.dateOfBirth != null)
						return false;
				} else if (!dateOfBirth.equals(other.dateOfBirth))
					return false;
				if (empName == null) {
					if (other.empName != null)
						return false;
				} else if (!empName.equals(other.empName))
					return false;
				if (gender == null) {
					if (other.gender != null)
						return false;
				} else if (!gender.equals(other.gender))
					return false;
				if (teamId == null) {
					if (other.teamId != null)
						return false;
				} else if (!teamId.equals(other.teamId))
					return false;
				return true;
			}
			 
		    
			
		    
}
