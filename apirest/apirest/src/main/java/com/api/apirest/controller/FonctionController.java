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


import com.api.apirest.model.Fonction;
import com.api.apirest.service.FonctionService;


@RestController
public class FonctionController {
	

	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private FonctionService fonctionService;
    
    /**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/fonction")
	
	public Fonction createFonction(@RequestBody Fonction fonction) {
		return fonctionService.saveFonction(fonction);
	}
    
    

    /**
    * Read - Get all functionss
    * @return - An Iterable object of Fonction full filled
    * permet d'appeler la méthode getFonctions() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi toutes les fonctions enregistrées en base de données.
    * 
    */
    @GetMapping("/fonctions")
    public Iterable<Fonction> getFonctions() {
        return fonctionService.getFonctions();
    }
    
    /**
	 * Read - Get one function
	 * @param id The id of the employee
	 * @return An function object full filled
	 */
	@GetMapping("/fonction/{id}")
	public Fonction getFonction(@PathVariable("id") final Long id) {
		Optional<Fonction> fonction = fonctionService.getFonction(id);
		if(fonction.isPresent()) {
			return fonction.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/majfonction/{id}")
	public Fonction updateFonction(@PathVariable("id") final Long id, @RequestBody Fonction fonction) {
		Optional<Fonction> f = fonctionService.getFonction(id);
		if(f.isPresent()) {
			Fonction currentFonction = f.get();
			
			String function = fonction.getFonction();
			if(function != null) {
				currentFonction.setFonction(function);
			}
					
			fonctionService.saveFonction(fonction);
			return currentFonction;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deletefonction/{id}")
	public void deleteFonction(@PathVariable("id") final Long id) {
		fonctionService.deleteFonction(id);
	}

}
