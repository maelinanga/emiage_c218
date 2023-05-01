package com.api.apirest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.apirest.model.Employee;
import com.api.apirest.repository.EmployeeRepository;


import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }
    public Optional <Employee> employeeByUserName(String nomutilisateur){
    	return employeeRepository.findByLogin(nomutilisateur);
    	}
    public Optional <Employee> employeeByNameandLastname(String name, String lastname){
    	return employeeRepository.findByFirstNameAndLastName(lastname, lastname);
    }

   public Iterable <Employee> employeeByDateEngagement(Long id,Date dateE){
	   
	   return employeeRepository.findByIdAndDateEngagement(id, dateE);
   }
} 
