package com.employee.services;

import com.employee.object.Employee;
import com.employee.persistence.EmployeeEntity;

public class PostCreateEmployeeService extends EmployeeServiceBase {

	@Override
	public void execute() {
		EmployeeEntity employeeEntity = mapEmployeeToEmployeeEntity.applyMapping(getEmployee());
		repository.save(employeeEntity);

	}
	@Override
	public Employee response() {
		return getEmployee();
	}

	@Override
	public boolean validate() {
		if (!repository.exists(getEmployee().getId())) {
			return true;
		}
		setEmployee(null);
		return false;
	}

}
