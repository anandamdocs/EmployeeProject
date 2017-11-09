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

import com.employee.mapper.MapEmployeeEntityToEmployee;
import com.employee.mapper.MapEmployeeToEmployeeEntity;
import com.employee.mapper.MapIterableEmployeeEntityToEmployeeList;
import com.employee.object.Employee;
import com.employee.object.Status;
import com.employee.persistence.EmployeeEntity;
import com.employee.persistence.EmployeeRepository;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.employee")
public class EmployeeApplication {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	MapEmployeeEntityToEmployee mapEmployeeEntityToEmployee;

	@Autowired
	MapEmployeeToEmployeeEntity mapEmployeeToEmployeeEntity;
	@Autowired
	MapIterableEmployeeEntityToEmployeeList mapIterableEmployeeEntityToEmployeeList;

	/**
	 * The API will return Employee based on Employee ID provided in input.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable(value = "id") Integer id) {
		EmployeeEntity employeeEntity = repository.findOne(new Integer(id));
		if (employeeEntity == null || employeeEntity.getStatus().equals(Status.INACTIVE.toString())) {
			return new ResponseEntity("Employee with id " + id + " not found or is not active", HttpStatus.NOT_FOUND);
		}

		Employee employee = mapEmployeeEntityToEmployee.applyMapper(employeeEntity);
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
		if(repository.exists(employee.getId())){
			return new ResponseEntity("Employee with id already exist", HttpStatus.BAD_REQUEST);
		}
		EmployeeEntity employeeEntity = mapEmployeeToEmployeeEntity.applyMapping(employee);
		repository.save(employeeEntity);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	/**
	 * Update Employee
	 * 
	 */
	@RequestMapping(value = "/updateEmployee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Integer id,@RequestBody Employee employee) {
		EmployeeEntity employeeEntity = repository.findOne(new Integer(id));
		if (employeeEntity == null) {
			return new ResponseEntity("Employee with id " + id + " not found", HttpStatus.NOT_FOUND);
		}
		employeeEntity.setStatus(employee.getStatus().toString());
		repository.save(employeeEntity);
		return new ResponseEntity<Employee>(HttpStatus.OK);
	}

	/**
	 * Delete Employee
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@RequestHeader(value="Authorization") String authorization,@PathVariable(value = "id") Integer id) {
		System.out.println(authorization);
		if(authorization == null || authorization.isEmpty() || !authorization.equals("manager")){
			return new ResponseEntity<String>("Insufficient privialge",HttpStatus.FORBIDDEN);
		}
		if(!repository.exists(id)){
			return new ResponseEntity<String>("Incorrect Employee ID",HttpStatus.BAD_REQUEST);
		}	
		repository.delete(id);
		return new ResponseEntity<String>("Employee Deleted Sucessfully",HttpStatus.OK);
	}

	/**
	 * List all the Employee
	 * 
	 * @return
	 */

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllEmployee() {
		Iterable<EmployeeEntity> employeeEntity = repository.findAll();
		if (employeeEntity == null) {
			return new ResponseEntity("No Employee Yet Registered", HttpStatus.NO_CONTENT);
		}
		List<Employee> employees = mapIterableEmployeeEntityToEmployeeList.applyMapping(employeeEntity);

		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}


	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
