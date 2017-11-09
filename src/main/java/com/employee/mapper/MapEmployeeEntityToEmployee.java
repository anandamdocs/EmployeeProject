package com.employee.mapper;

import org.springframework.beans.factory.annotation.Qualifier;

import com.employee.object.Employee;
import com.employee.object.Status;
import com.employee.persistence.EmployeeEntity;

@Qualifier
public class MapEmployeeEntityToEmployee {

	public Employee applyMapper(EmployeeEntity employeeEntity){
		Employee employee = new Employee();
		employee.setDateOfBirth(employeeEntity.getDob());
		employee.setDateOfEmployment(employeeEntity.getDoe());
		employee.setFirstName(employeeEntity.getFirstName());
		employee.setId(employeeEntity.getId());
		employee.setLastName(employeeEntity.getLastName());
		employee.setMiddleInitial(employeeEntity.getMiddleName());
		if(employeeEntity.getStatus().equals("ACTIVE")){
			employee.setStatus(Status.ACTIVE);
		}else{
			employee.setStatus(Status.INACTIVE);
		}
		return employee;
	}
}
