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


import com.api.apirest.model.Leaves;

import com.api.apirest.service.LeavesService;

@RestController

public class LeavesController {
	
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private LeavesService leaveService;
    
    /**
	 * Create - Add a new type de congé
	 * @param leave An object employee
	 * @return The leave object saved
	 */
	@PostMapping("/conge")
	
	public Leaves createEmployee(@RequestBody Leaves leave) {
		return leaveService.saveLeave(leave);
	}
    
    

    /**
    * Read - Get all leaves
    * @return - An Iterable object of leaves full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
    * 
    */
    @GetMapping("/conges")
    public Iterable<Leaves> getLeaves() {
        return leaveService.getLeaves();
    }
    
    /**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("/conge/{id}")
	public Leaves getLeave(@PathVariable("id") final Long id) {
		Optional<Leaves> leave = leaveService.getLeaves(id);
		if(leave.isPresent()) {
			return leave.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/majconge/{id}")
	public Leaves updateLeave(@PathVariable("id") final Long id, @RequestBody Leaves leave) {
		Optional<Leaves> e = leaveService.getLeaves(id);
		if(e.isPresent()) {
			Leaves currentleave = e.get();
			
			String conge = leave.getTypeconge();
			if(conge != null) {
				currentleave.setTypeconge(conge);
			}
			int joursconge = leave.getNbrJours();
			if(joursconge != 0) {
				currentleave.setNbrJours(joursconge);
			}
			
			
			
			
			leaveService.saveLeave(leave);
			return currentleave;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deleteconge/{id}")
	public void deleteLeave(@PathVariable("id") final Long id) {
		leaveService.deleteLeave(id);
	}

}
