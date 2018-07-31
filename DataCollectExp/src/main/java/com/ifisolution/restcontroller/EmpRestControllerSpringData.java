package com.ifisolution.restcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifisolution.model.EmpSpringData;
import com.ifisolution.service.EmpService;
@RestController
@RequestMapping("/api/springdata")
public class EmpRestControllerSpringData {
	private static final Log LOGGER = LogFactory.getLog(EmpRestControllerSpringData.class);
	@Autowired
	EmpService empService;
		//get all employee3
			@GetMapping("/employees")
			public @ResponseBody Payload getAllEmployee3() {
				Payload message = new Payload();
				Object data = null;
				try {
					data = empService.getAllEmp3();
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
		//add a employee 3
			@PostMapping("/employees")
			public @ResponseBody Payload addEmployee3(@RequestBody EmpSpringData emp) {
				Payload message = new Payload();
				Object data = null;
				try {
					if(empService.addOrUpdateEmp3(emp)==true) {
									message.setStatus("OK");
									message.setMessage("add employee successfully");
									message.setData(emp);
									message.setError(false);
									LOGGER.info("add employee successfully!!!");
					}
				} catch (Exception e) {
					message.setStatus("FAILED");
					message.setMessage("add employee unsuccessfully, error: "+ e.getMessage());
					message.setData(data);
					message.setError(true);
					LOGGER.error("add employee unsuccessfully!!!");
				}

				return message;
				
			}
		//add a employee 3
			@PutMapping("/employees")
			public @ResponseBody Payload updateEmployee3(@RequestBody EmpSpringData emp) {
				Payload message = new Payload();
				Object data = null;
				try {
					if(empService.addOrUpdateEmp3(emp)==true) {
									message.setStatus("OK");
									message.setMessage("update employee successfully");
									message.setData(emp);
									message.setError(false);
									LOGGER.info("update employee successfully!!!");
					}
				} catch (Exception e) {
					message.setStatus("FAILED");
					message.setMessage("update employee unsuccessfully, error: "+ e.getMessage());
					message.setData(data);
					message.setError(true);
					LOGGER.error("update employee unsuccessfully!!!");
				}

				return message;
				
			}	
			//delete a employee 3
			@DeleteMapping("/employees")
			public @ResponseBody Payload deleteEmployee3(@RequestParam String empName,@RequestParam String date) throws ParseException {
				Payload message = new Payload();
				Object data = null;
				TimeZone tz=TimeZone.getTimeZone("GMT");
				 final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  sdf.setTimeZone(tz);
				  Date date_of_birth=sdf.parse(date);
				try {
					if(empService.deleteEmp3(empName,date_of_birth)==true) {
									message.setStatus("OK");
									message.setMessage("delete employee successfully");
									message.setData(data);
									message.setError(false);
									LOGGER.info("delete employee successfully!!!");
					}
				} catch (Exception e) {
					message.setStatus("FAILED");
					message.setMessage("delete employee unsuccessfully, error: "+ e.getMessage());
					message.setData(data);
					message.setError(true);
					LOGGER.error("delete employee unsuccessfully!!!");
				}

				return message;
				
			}
}
