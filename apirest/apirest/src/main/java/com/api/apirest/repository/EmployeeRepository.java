package com.api.apirest.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
	Optional <Employee> findByFirstNameAndLastName(String firstName,String lastName);
	Optional <Employee> findByLogin(String login);
	Iterable <Employee> findByIdAndDateEngagement(Long id,Date dateEngagement);
	
}
