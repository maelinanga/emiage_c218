package com.api.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.api.apirest.model.Retenues;
import com.api.apirest.service.RetenuesService;

@RestController
public class RetenuesController {
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private RetenuesService retenueService;
    
    /**
	 * Create - Add a new line deduction
	 * @param leave An object deduction
	 * @return The deduction object saved
	 */
	@PostMapping("/retenue")
	
	public Retenues createRetenue(@RequestBody Retenues retenue) {
		return retenueService.saveRetenue(retenue);
	}
    
    

    /**
    * Read - Get all deductions
    * @return - An Iterable object of deduction full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi toutes les retenues enregistrées en base des données.
    * 
    */
    @GetMapping("/retenues")
    public Iterable<Retenues> getPRetenues() {
        return retenueService.getRetenues();
    }
    
    /**
	 * Read - Get one line deduction
	 * @param id The id of the pay
	 * @return An  payroll line object full filled
	 */
	@GetMapping("/retenue/{id}")
	public Retenues getPrime(@PathVariable("id") final Long id) {
		Optional<Retenues> deduction = retenueService.getRetenue(id);
		if(deduction.isPresent()) {
			return deduction.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing payroll line
	 * @param id - The id of payroll to update
	 * @param employee - The pay object updated
	 * @return
	 */
	@PutMapping("/majretenue/{id}")
	public Retenues updateRetenue(@PathVariable("id") final Long id, @RequestBody Retenues retenue) {
		Optional<Retenues> p = retenueService.getRetenue(id);
		if(p.isPresent()) {
			Retenues currentretenue = p.get();
			
			String typeretenue = retenue.getRetenue();
			if(typeretenue != null) {
				currentretenue.setRetenue(typeretenue);
			}
			
						
			retenueService.saveRetenue(retenue);
			return currentretenue;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an deduction
	 * @param id - The id of the deduction to delete
	 */
	@DeleteMapping("/deleteretenue/{id}")
	public void deleteRetenue(@PathVariable("id") final Long id) {
		retenueService.deleteRetenue(id);
	}

}
