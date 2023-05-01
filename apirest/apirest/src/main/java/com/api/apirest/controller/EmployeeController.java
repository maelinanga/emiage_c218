package com.api.apirest.controller;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.apirest.model.Employee;
import com.api.apirest.service.EmployeeService;


@RestController
public class EmployeeController {

	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private EmployeeService employeeService;
    
    /**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/employee")
	
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
    
    

    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
    * 
    */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
    
    /**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") final Long id) {
		Optional<Employee> employee = employeeService.getEmployee(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/loginemployee/{username}")
	public Employee getUserName(@PathVariable("username") final String username) {
		Optional <Employee> employee = employeeService.employeeByUserName(username);
		return employee.get();
		
		
	}
	
	 /* Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/majemployee/{id}")
	public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
		Optional<Employee> e = employeeService.getEmployee(id);
		if(e.isPresent()) {
			Employee currentEmployee = e.get();
			
			String firstName = employee.getFirstName();
			if(firstName != null) {
				currentEmployee.setFirstName(firstName);
			}
			String lastName = employee.getLastName();
			if(lastName != null) {
				currentEmployee.setLastName(lastName);
			}
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			
			String sexe = employee.getSexe();
			if(sexe != null) {
				currentEmployee.setSexe(sexe);
			}
			
			String adresse = employee.getAdresse();
			if(adresse != null) {
				currentEmployee.setAdresse(adresse);
			}
			
			String telephone = employee.getTelephone();
			if(telephone != null) {
				currentEmployee.setTelephone(telephone);
			}
			
			String etatcivil = employee.getEtatcivil();
			if(etatcivil != null) {
				currentEmployee.setEtatcivil(etatcivil);
			}
			
			int enfants = employee.getEnfants();
			if(enfants != 0) {
				currentEmployee.setEnfants(enfants);
			}
			
			Date datenaiss = employee.getDateNaissance();
			if(datenaiss != null) {
				currentEmployee.setDateNaissance(datenaiss);
			}
			
			Date datengagement = employee.getDateEngagement();
			
			if(datengagement != null) {
				currentEmployee.setDateEngagement(datengagement);
			}
			
			String login=employee.getLogin();
			if(login!=null) {
				currentEmployee.setLogin(login);
			}
			
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);
			}
			
			int roleemp = employee.getRole();
			if(roleemp != 0) {
				currentEmployee.setRole(roleemp);
			}
			
			
			
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deleteemployee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
	}

}
