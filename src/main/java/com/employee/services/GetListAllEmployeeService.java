package com.employee.services;

import java.util.List;

import com.employee.object.Employee;
import com.employee.persistence.EmployeeEntity;

public class GetListAllEmployeeService extends EmployeeServiceBase {
	
	private List<Employee> employees = null;
 
	@Override
	public void execute() {
		Iterable<EmployeeEntity> employeeEntity = repository.findAll();
		if (employeeEntity == null) {
			return;
		}
		employees = mapIterableEmployeeEntityToEmployeeList.applyMapping(employeeEntity);

	}
	
	public List<Employee> response(){
		return employees;
	}

	@Override
	public boolean validate() {
		return true;
	}

}
