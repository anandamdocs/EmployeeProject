package com.employee.mapper;

import com.employee.object.Employee;
import com.employee.persistence.EmployeeEntity;

public class MapEmployeeToEmployeeEntity {

	public EmployeeEntity applyMapping(Employee employee){
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setDob(employee.getDateOfBirth());
		employeeEntity.setDoe(employee.getDateOfEmployment());
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		employeeEntity.setId(employee.getId());
		employeeEntity.setMiddleName(employee.getMiddleInitial());
		employeeEntity.setStatus(employee.getStatus().toString());
		return employeeEntity;
	}
}
