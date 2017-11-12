package com.employee.services;

import com.employee.object.Employee;
import com.employee.persistence.EmployeeEntity;
/**
 * Service class to retrieve Employee Service.
 * @author ANANDRA
 *
 */
public class GetEmployeeService extends EmployeeServiceBase {
	public void execute(){
		EmployeeEntity employeeEntity = repository.findOne(new Integer(getId()));
		if(employeeEntity != null){
			employee = mapEmployeeEntityToEmployee.applyMapper(employeeEntity);
		}
	}
	public Employee response(){
		return getEmployee();
	}
	@Override
	public boolean validate() {
		return true;
	}
}
