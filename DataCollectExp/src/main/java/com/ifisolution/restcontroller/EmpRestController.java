package com.ifisolution.restcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifisolution.model.Emp;
import com.ifisolution.service.EmpService;

@RestController
@RequestMapping("/api")
public class EmpRestController {
	private static final Log LOGGER = LogFactory.getLog(EmpRestController.class);
	@Autowired
	private EmpService empService;
	//get all employee
	@GetMapping("/employees")
	public @ResponseBody Payload getAllEmployee() {
		Payload message = new Payload();
		Object data = null;
		try {
			data = empService.getAllEmp();
			message.setStatus("OK");
			message.setMessage("Get all message successfully!!!");
			message.setData(data);
			message.setError(false);
			LOGGER.info("get all message successfully!!!");
		} catch (Exception e) {
			message.setStatus("FAILED");
			message.setMessage("Get all message unsuccessfully, error: "+ e.getMessage());
			message.setData(data);
			message.setError(true);
			LOGGER.error("get all message unsuccessfully!!!");
		}

		return message;
	}
	
	//get all employee 2
		@GetMapping("/employees2")
		public @ResponseBody Payload getAllEmployee2() {
			Payload message = new Payload();
			Object data = null;
			try {
				data = empService.getAllEmp2();
				message.setStatus("OK");
				message.setMessage("Get all message successfully!!!");
				message.setData(data);
				message.setError(false);
				LOGGER.info("get all message successfully!!!");
			} catch (Exception e) {
				message.setStatus("FAILED");
				message.setMessage("Get all message unsuccessfully, error: "+ e.getMessage());
				message.setData(data);
				message.setError(true);
				LOGGER.error("get all message unsuccessfully!!!");
			}

			return message;
		}
	
	//add a employee
	@PostMapping("/employees")
	public @ResponseBody Payload addEmployee(@RequestBody Emp emp) {
		Payload message = new Payload();
		Object data = null;
		try {
			if(empService.addEmp(emp)==true) {
							message.setStatus("OK");
							message.setMessage("add employee successfully");
							message.setData(emp);
							message.setError(false);
							LOGGER.info("add employee successfully successfully!!!");
			}
		} catch (Exception e) {
			message.setStatus("FAILED");
			message.setMessage("add employee successfully unsuccessfully, error: "+ e.getMessage());
			message.setData(data);
			message.setError(true);
			LOGGER.error("add employee successfully unsuccessfully!!!");
		}

		return message;
		
	}
	
	//add a employee 2
		@PostMapping("/employees2")
		public @ResponseBody Payload addEmployee2(@RequestBody Emp emp) {
			Payload message = new Payload();
			Object data = null;
			
			try {
				if(empService.addEmp2(emp)==true) {
								message.setStatus("OK");
								message.setMessage("add employee successfully");
								message.setData(emp);
								message.setError(false);
								LOGGER.info("add employee successfully successfully!!!");
				}
			} catch (Exception e) {
				message.setStatus("FAILED");
				message.setMessage("add employee successfully unsuccessfully, error: "+ e.getMessage());
				message.setData(data);
				message.setError(true);
				LOGGER.error("add employee successfully unsuccessfully!!!");
			}

			return message;
			
		}
	
	//edit a employee
		@PutMapping("/employees")
		public @ResponseBody Payload editEmployee(@RequestBody Emp emp) {
			Payload message = new Payload();
			Object data = null;
			
			try {
				if(empService.addEmp(emp)==true) {
								message.setStatus("OK");
								message.setMessage("update employee successfully");
								message.setData(emp);
								message.setError(false);
								LOGGER.info("update employee successfully successfully!!!");
				}
			} catch (Exception e) {
				message.setStatus("FAILED");
				message.setMessage("update employee successfully unsuccessfully, error: "+ e.getMessage());
				message.setData(data);
				message.setError(true);
				LOGGER.error("update employee successfully unsuccessfully!!!");
			}

			return message;
			
		}
		
		//delete a employee 
				@DeleteMapping("/employees")
				public @ResponseBody Payload editEmployee(@RequestParam String emp_name,
														  @RequestParam String dateofbirth) throws ParseException {
					Payload message = new Payload();
					Object data = null;
					TimeZone tz=TimeZone.getTimeZone("GMT");
					 final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					  sdf.setTimeZone(tz);
					  Date date_of_birth=sdf.parse(dateofbirth);
					try {
						if(empService.deleteEmp(emp_name,date_of_birth)==true) {
										message.setStatus("OK");
										message.setMessage("Delete employee successfully");
										message.setData(data);
										message.setError(false);
										LOGGER.info("Delete employee successfully successfully!!!");
						}
						else {
							message.setStatus("OK");
							message.setMessage("employee not found!");
							message.setData(data);
							message.setError(false);
							LOGGER.info("employee not found!");
						}
						
					} catch (Exception e) {
						message.setStatus("FAILED");
						message.setMessage("Delete employee successfully unsuccessfully, error: "+ e.getMessage());
						message.setData(data);
						message.setError(true);
						LOGGER.error("Delete employee successfully unsuccessfully!!!");
					}

					return message;
					
				}
				
				//delete a employee 2
				@DeleteMapping("/employees2")
				public @ResponseBody Payload editEmployee2(@RequestParam String emp_name,
														  @RequestParam String date_of_birth) throws ParseException {
					Payload message = new Payload();
					Object data = null;
					TimeZone tz=TimeZone.getTimeZone("GMT");
					 final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					  sdf.setTimeZone(tz);
					  Date dateofbirth=sdf.parse(date_of_birth); //change string to date
					try {
						if(empService.deleteEmp2(emp_name,dateofbirth)==true) {
										message.setStatus("OK");
										message.setMessage("Delete employee successfully");
										message.setData(data);
										message.setError(false);
										LOGGER.info("Delete employee successfully successfully!!!");
						}
						else {
							message.setStatus("OK");
							message.setMessage("employee not found!");
							message.setData(data);
							message.setError(false);
							LOGGER.info("employee not found!");
						}
						
					} catch (Exception e) {
						message.setStatus("FAILED");
						message.setMessage("Delete employee successfully unsuccessfully, error: "+ e.getMessage());
						message.setData(data);
						message.setError(true);
						LOGGER.error("Delete employee successfully unsuccessfully!!!");
					}

					return message;
					
				}	
				//get emp by team id
				@GetMapping("/employees/team/{teamId}")
				public @ResponseBody Payload getEmpByTeamId(@PathVariable String teamId) {
					Payload message = new Payload();
					Object data = null;
					try {
						data = empService.getEmpByTeamId(teamId);
						message.setStatus("OK");
						message.setMessage("Get emp by team id successfully!!!");
						message.setData(data);
						message.setError(false);
						LOGGER.info("Get emp by team id successfully!!!");
					} catch (Exception e) {
						message.setStatus("FAILED");
						message.setMessage("Get emp by team id unsuccessfully, error: "+ e.getMessage());
						message.setData(data);
						message.setError(true);
						LOGGER.error("Get emp by team id unsuccessfully!!!");
					}

					return message;
				}		
				
				//get emp by gender
				@GetMapping("/employees/gender/{gender}")
				public @ResponseBody Payload getEmpByGender(@PathVariable String gender) {
					Payload message = new Payload();
					Object data = null;
					Boolean gen=null;
					try {
						
						if(gender.equals("male")) {
							gen=true;
							data = empService.getEmpByGender(gen);
							message.setStatus("OK");
							message.setMessage("Get emp by gender successfully!!!");
							message.setData(data);
							message.setError(false);
							LOGGER.info("Get emp by gender successfully!!!");
						}
							else if(gender.equals("female")) {
								gen=false;
								data = empService.getEmpByGender(gen);
								message.setStatus("OK");
								message.setMessage("Get emp by gender successfully!!!");
								message.setData(data);
								message.setError(false);
								LOGGER.info("Get emp by gender successfully!!!");
							}
								else if(gender.equals("LGBT")) {
									gen=null;
									data = empService.getEmpByGender(gen);
									message.setStatus("OK");
									message.setMessage("Get emp by gender successfully!!!");
									message.setData(data);
									message.setError(false);
									LOGGER.info("Get emp by gender successfully!!!");
								}
							
									else {
										message.setStatus("OK");
										message.setMessage("Gender must be male, female or LGBT!!");
										message.setData(data);
										message.setError(false);
										LOGGER.info("Gender must be male, female or LGBT!!");
									}
					} catch (Exception e) {
						message.setStatus("FAILED");
						message.setMessage("Get emp by gender unsuccessfully, error: "+ e.getMessage());
						message.setData(data);
						message.setError(true);
						LOGGER.error("Get emp by gender unsuccessfully!!!");
					}

					return message;
				}		
}
