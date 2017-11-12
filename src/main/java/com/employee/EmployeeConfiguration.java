package com.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.mapper.MapEmployeeEntityToEmployee;
import com.employee.mapper.MapEmployeeToEmployeeEntity;
import com.employee.mapper.MapIterableEmployeeEntityToEmployeeList;
import com.employee.services.DeleteEmployeeService;
import com.employee.services.GetEmployeeService;
import com.employee.services.GetListAllEmployeeService;
import com.employee.services.PostCreateEmployeeService;
import com.employee.services.PutUpdateEmployeeService;

/**
 * Configuration file containing bean definition
 * @author ANANDRA
 *
 */
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

	
	@Bean
	public GetEmployeeService getEmployeeService(){
		return new GetEmployeeService();
		
	}
	
	@Bean
	public PostCreateEmployeeService postCreateEmployeeService(){
		return new PostCreateEmployeeService();
		
	}
	
	
	@Bean
	public DeleteEmployeeService deleteEmployeeService(){
		return new DeleteEmployeeService();
		
	}
	
	@Bean
	public GetListAllEmployeeService getListAllEmployeeService(){
		return new GetListAllEmployeeService();
		
	}
	
	@Bean
	public PutUpdateEmployeeService putUpdateEmployeeService(){
		return new PutUpdateEmployeeService();
		
	}
	
}
