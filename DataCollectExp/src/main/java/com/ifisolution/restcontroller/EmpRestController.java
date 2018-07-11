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
		Object data = "";
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
	
	//add a employee
	@PostMapping("/employees")
	public @ResponseBody Payload addEmployee(@RequestBody Emp emp) {
		Payload message = new Payload();
		Object data = "";
		
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
	
	//edit a employee
		@PutMapping("/employees")
		public @ResponseBody Payload editEmployee(@RequestBody Emp emp) {
			Payload message = new Payload();
			Object data = "";
			
			try {
				if(empService.updateEmp(emp)==true) {
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
					Object data = "";
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
}