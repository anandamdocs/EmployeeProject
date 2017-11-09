package com.employee.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.employee.object.Employee;
import com.employee.object.Status;
import com.employee.persistence.EmployeeEntity;

public class MapIterableEmployeeEntityToEmployeeList {
	
	
	@Autowired
	MapEmployeeEntityToEmployee mapEmployeeEntityToEmployee;
	
	public List<Employee> applyMapping(Iterable<EmployeeEntity> employees){
		List<Employee> employeesList = new ArrayList<>();
		for(EmployeeEntity employeeEntity : employees){
			if(employeeEntity.getStatus().equals(Status.ACTIVE.toString())){
				Employee employee = mapEmployeeEntityToEmployee.applyMapper(employeeEntity);
				employeesList.add(employee);
			}
		}
		return employeesList;
	}

}
