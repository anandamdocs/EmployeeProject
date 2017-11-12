package com.employee.services;

public class DeleteEmployeeService extends EmployeeServiceBase {
	
	private Boolean status;

	@Override
	public void execute() {
		repository.delete(getId());
		status= true;
	}


	@Override
	public boolean validate() {
		if(getId()==null){
			return false;
		}
		return true;
	}



	@Override
	public Object response() {
		return status;
	}
}
