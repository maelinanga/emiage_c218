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

import com.api.apirest.model.AdminEmployees;
import com.api.apirest.model.Employee;
import com.api.apirest.model.Fonction;
import com.api.apirest.model.Grade;
import com.api.apirest.service.AdminEmployeeService;


@RestController
public class AdminEmployeeController {
	
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private AdminEmployeeService adminService;
    
    /**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/adminemployee")
	
	public AdminEmployees createAdminEmployee(@RequestBody AdminEmployees adminemployee) {
		return adminService.saveAdminEmployee(adminemployee);
	}
    
    

    /**
    * Read - Get all employees
    * @return - An Iterable object of Employee full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
    * 
    */
    @GetMapping("/adminemployees")
    public Iterable<AdminEmployees> getAdminEmployees() {
        
    	return adminService.getAdminEmployees();
    }
    
    /**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/adminemployee/{id}")
	public AdminEmployees getAdminEmployee(@PathVariable("id") final Long id) {
		Optional<AdminEmployees> admin = adminService.getAdminEmployee(id);
		if(admin.isPresent()) {
			return admin.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/majadminemployee/{id}")
	public AdminEmployees updateAdminEmployee(@PathVariable("id") final Long id, @RequestBody AdminEmployees adminemployee) {
		Optional<AdminEmployees> e = adminService.getAdminEmployee(id);
		
		if(e.isPresent()) {
			
			AdminEmployees currentEmployee = e.get();
			
			Date dateFonction = adminemployee.getDateFonction();
			if(dateFonction != null) {
				currentEmployee.setDateFonction(dateFonction);
			}
			
			float honoraire = adminemployee.getHonoraires();
			if(honoraire != 0.0) {
				currentEmployee.setHonoraires(honoraire);
			}
			
			String nif = adminemployee.getNif();
			if(nif != null) {
				currentEmployee.setNif(nif);
			}
			
			String numeroCompte = adminemployee.getNumeroCompte();
			if(numeroCompte != null) {
				currentEmployee.setNumeroCompte(numeroCompte);
			}
			
			String banque = adminemployee.getBanque();
			if(banque != null) {
				currentEmployee.setBanque(banque);
			}
			
			Grade grade = adminemployee.getGrade();
			if(grade != null) {
				currentEmployee.setGrade(grade);
			}
			
			Fonction fonction = adminemployee.getFonction();
			if(fonction != null) {
				currentEmployee.setFonction(fonction);
			}
			
			Employee employee = adminemployee.getEmployee();
			if(employee != null) {
				currentEmployee.setEmployee(employee);
			}
			
			float tauxImpot = adminemployee.getTauxImpot();
			if(tauxImpot != 0.0) {
				currentEmployee.setTauxImpot(tauxImpot);
			}
			
			
			
			
			adminService.saveAdminEmployee(adminemployee);
			return currentEmployee;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deleteadminemployee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		adminService.deleteAdminEmployee(id);
	}
	

}
