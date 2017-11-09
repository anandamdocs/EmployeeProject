package com.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.mapper.MapEmployeeEntityToEmployee;
import com.employee.mapper.MapEmployeeToEmployeeEntity;
import com.employee.mapper.MapIterableEmployeeEntityToEmployeeList;

@Configuration
public class EmployeeConfiguration {

	@Bean
	public MapEmployeeEntityToEmployee mapEmployeeEntityToEmployee(){
		return new MapEmployeeEntityToEmployee();
	}

	@Bean
	public MapEmployeeToEmployeeEntity mapEmployeeToEmployeeEntity(){
		return new MapEmployeeToEmployeeEntity();
		
	}
	
	@Bean
	public MapIterableEmployeeEntityToEmployeeList mapIterableEmployeeEntityToEmployeeList(){
		return new MapIterableEmployeeEntityToEmployeeList();
		
	}
}
