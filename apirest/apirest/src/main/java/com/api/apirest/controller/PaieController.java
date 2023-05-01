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

import com.api.apirest.model.Employee;
import com.api.apirest.model.Paie;
import com.api.apirest.model.Primes;
import com.api.apirest.model.Retenues;
import com.api.apirest.service.PaieService;

@RestController
public class PaieController {
	
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private PaieService paieService;
    
    /**
	 * Create - Add a new line paie
	 * @param leave An object employee
	 * @return The leave object saved
	 */
	@PostMapping("/paie")
	
	public Paie createEmployee(@RequestBody Paie paie) {
		return paieService.savePaie(paie);
	}
    
	 @GetMapping("/paie/{employee}")
	 public Iterable <Paie> findByEmployee_Id(@PathVariable("employee") Integer employee){
	   
		 return paieService.findByEmployee_Id(employee);
		 
	   }
	 
	 @GetMapping("/paie/{year}/{month}")
	 public Iterable <Paie> findByMonthAndYear(@PathVariable("year")  Integer year,@PathVariable("month") Integer month ){
	   
		return paieService.findByMonthAndYear(year, month);
		
	   }
	 
	 @GetMapping("/paie/{employee}/{month}/{year}")
	 public Paie findByEmployee_IdAndMonthAndYear(@PathVariable("employee")  Integer employee,@PathVariable("year")  Integer year,@PathVariable("month") Integer month ){
	   
		 Optional<Paie> paie = paieService.findByEmployee_IdAndMonthAndYear(employee, year, month);
			if(paie.isPresent()) {
				return paie.get();
			} else {
				return null;
			}
		
		 
		
	   }

    /**
    * Read - Get all Paie
    * @return - An Iterable object of Paie full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi tous les employés enregistrés en base de données.
    * 
    */
    @GetMapping("/paies")
    public Iterable<Paie> getPaies() {
        return paieService.getPaies();
    }
    
    /**
	 * Read - Get one line payroll 
	 * @param id The id of the pay
	 * @return An  payroll line object full filled
	 */
	@GetMapping("/paie/{id}")
	public Paie getPaie(@PathVariable("id") final Long id) {
		Optional<Paie> paie = paieService.getPaie(id);
		if(paie.isPresent()) {
			return paie.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing payroll line
	 * @param id - The id of payroll to update
	 * @param employee - The pay object updated
	 * @return
	 */
	@PutMapping("/majpaie/{id}")
	public Paie updatePaie(@PathVariable("id") final Long id, @RequestBody Paie paie) {
		Optional<Paie> p = paieService.getPaie(id);
		if(p.isPresent()) {
			Paie currentpaie = p.get();
			
			int mois = paie.getMois();
			if(mois != 0) {
				currentpaie.setMois(mois);
			}
			
			int annee = paie.getAnnee();
			if(annee != 0) {
				currentpaie.setAnnee(annee);
			}
			
			float montantretenue =paie.getMontantretenue();
			if(montantretenue != 0.00) {
				currentpaie.setMontantretenue(montantretenue);
			}
			
			float montantprime = paie.getMontantprime();
			if(montantprime != 0.00) {
				currentpaie.setMontantprime(montantprime);
			}
			
			float salairenet =paie.getSalaireNet();
			if(salairenet != 0.00) {
				currentpaie.setSalaireNet(salairenet);
			}
			
			Retenues typeretenue = paie.getRetenue();
			if(typeretenue != null) {
				currentpaie.setRetenue(typeretenue);
			}
			
			Primes typeprime = paie.getPrime();
			if(typeprime != null) {
				currentpaie.setPrime(typeprime);
			}
		
			Employee employee=paie.getEmployee();
			if(employee != null) {
				currentpaie.setEmployee(employee);
			}
			
			paieService.savePaie(paie);
			return currentpaie;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/deletepaie/{id}")
	public void deletePaie(@PathVariable("id") final Long id) {
		paieService.deletePaie(id);
	}

}
