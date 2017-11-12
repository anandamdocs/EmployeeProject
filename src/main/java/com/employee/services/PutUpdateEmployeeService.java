package com.employee.services;

import com.employee.persistence.EmployeeEntity;

public class PutUpdateEmployeeService extends EmployeeServiceBase {

	private Boolean status;
	@Override
	public void execute() {
		EmployeeEntity employeeEntity = repository.findOne(new Integer(getId()));
		if(employeeEntity==null){
			status=false;
			return;
		}
		employeeEntity.setStatus(employee.getStatus().toString());
		repository.save(employeeEntity);
		status=true;
	}
	
	public Boolean response(){
		return status;
	}

	@Override
	public boolean validate() {
		return true;
	}

}
