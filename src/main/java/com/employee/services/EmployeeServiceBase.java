package com.employee.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.mapper.MapEmployeeEntityToEmployee;
import com.employee.mapper.MapEmployeeToEmployeeEntity;
import com.employee.mapper.MapIterableEmployeeEntityToEmployeeList;
import com.employee.object.Employee;
import com.employee.persistence.EmployeeRepository;

/**
 * Base class all service layer class should implement
 * contain common parameters.
 * @author ANANDRA
 *
 */

abstract class EmployeeServiceBase {

	@Autowired
	MapEmployeeEntityToEmployee mapEmployeeEntityToEmployee;

	@Autowired
	MapEmployeeToEmployeeEntity mapEmployeeToEmployeeEntity;
	
	@Autowired
	MapIterableEmployeeEntityToEmployeeList mapIterableEmployeeEntityToEmployeeList;
	
	@Autowired
	EmployeeRepository repository;

	
	protected Employee employee = null;
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	private Integer id;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * each service class should have execute method
	 */
	public abstract void execute();
	
	/**
	 * return service response
	 * @return
	 */
	public abstract Object response();

	/**
	 * return outputmapping
	 */
	public abstract boolean validate();

	/**
	 * Only run the validation method if execution is successful
	 */
	public void run(){
		if(validate()){
			execute();
		}
	}

}
