package com.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.object.Employee;
import com.employee.object.Status;
import com.employee.services.DeleteEmployeeService;
import com.employee.services.GetEmployeeService;
import com.employee.services.GetListAllEmployeeService;
import com.employee.services.PostCreateEmployeeService;
import com.employee.services.PutUpdateEmployeeService;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.employee")
public class EmployeeApplication {

	/**
	 * Service layer initialization
	 */
	
	@Autowired
	GetEmployeeService getEmployeeService;

	@Autowired
	PostCreateEmployeeService postCreateEmployeeService;
	
	@Autowired
	PutUpdateEmployeeService updateEmployeeService;
	
	@Autowired
	DeleteEmployeeService deleteEmployeeService;
	
	@Autowired
	GetListAllEmployeeService getListAllEmployeeService;
	/**
	 * The API will return Employee based on Employee ID provided in input.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable(value = "id") Integer id) {
		// invoke service layer
		getEmployeeService.setId(id);
		getEmployeeService.run();
		//create rest response
		Employee employee = getEmployeeService.response();
		if (employee == null || employee.getStatus().equals(Status.INACTIVE.toString())) {
			return new ResponseEntity("Employee with id " + id + " not found or is not active", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * The API will create Employee
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		postCreateEmployeeService.setEmployee(employee);
		postCreateEmployeeService.run();
		Employee employeeResponse = postCreateEmployeeService.response();
		if(employeeResponse == null){
			return new ResponseEntity("Employee with id already exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Employee>(employeeResponse,HttpStatus.CREATED);
	}
	
	/**
	 * Update Employee
	 * 
	 */
	@RequestMapping(value = "/updateEmployee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id,@RequestBody Employee employee) {
		
		updateEmployeeService.setEmployee(employee);
		updateEmployeeService.setId(id);
		updateEmployeeService.run();
		if (!updateEmployeeService.response()) {
			return new ResponseEntity("Employee with id " + id + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

	/**
	 * Delete Employee
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@RequestHeader(value="Authorization") String authorization,@PathVariable(value = "id") Integer id) {
		if(authorization == null || authorization.isEmpty() || !authorization.equals("manager")){
			return new ResponseEntity<String>("Insufficient privialge",HttpStatus.FORBIDDEN);
		}
		deleteEmployeeService.setId(id);
		deleteEmployeeService.run();
		Boolean status = (Boolean)deleteEmployeeService.response();
		
		if(!status){
			return new ResponseEntity<String>("Incorrect Employee ID",HttpStatus.BAD_REQUEST);
		}	
		
		return new ResponseEntity<String>("Employee Deleted Sucessfully",HttpStatus.OK);
	}

	/**
	 * List all the Employee
	 * 
	 * @return
	 */

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllEmployee() {
		getListAllEmployeeService.run();
		if (getListAllEmployeeService.response() == null) {
			return new ResponseEntity("No Employee Yet Registered", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(getListAllEmployeeService.response() , HttpStatus.OK);
	}


	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
