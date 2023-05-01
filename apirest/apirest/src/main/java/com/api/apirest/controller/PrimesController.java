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


import com.api.apirest.model.Primes;
import com.api.apirest.service.PrimesService;

@RestController
public class PrimesController {
	
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private PrimesService primeService;
    
    /**
	 * Create - Add a new line paie
	 * @param leave An object employee
	 * @return The leave object saved
	 */
	@PostMapping("/prime")
	
	public Primes createPrime(@RequestBody Primes prime) {
		return primeService.savePrime(prime);
	}
    
    

    /**
    * Read - Get all Paie
    * @return - An Iterable object of Paie full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
    * 
    */
    @GetMapping("/primes")
    public Iterable<Primes> getPrimes() {
        return primeService.getPrimes();
    }
    
    /**
	 * Read - Get one line payroll 
	 * @param id The id of the pay
	 * @return An  payroll line object full filled
	 */
	@GetMapping("/prime/{id}")
	public Primes getPrime(@PathVariable("id") final Long id) {
		Optional<Primes> prime = primeService.getPrime(id);
		if(prime.isPresent()) {
			return prime.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing payroll line
	 * @param id - The id of payroll to update
	 * @param employee - The pay object updated
	 * @return
	 */
	@PutMapping("/majprime/{id}")
	public Primes updatePrime(@PathVariable("id") final Long id, @RequestBody Primes prime) {
		Optional<Primes> p = primeService.getPrime(id);
		if(p.isPresent()) {
			Primes currentprime = p.get();
			
			String typeprime = prime.getPrime();
			if(typeprime != null) {
				currentprime.setPrime(typeprime);
			}
			
						
			primeService.savePrime(currentprime);
			return currentprime;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deleteprime/{id}")
	public void deletePrime(@PathVariable("id") final Long id) {
		primeService.deletePrime(id);
	}

}
