package com.employee.persistence;

import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author ANANDRA
 *
 */

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

}
