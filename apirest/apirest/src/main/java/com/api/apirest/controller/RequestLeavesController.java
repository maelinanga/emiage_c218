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
import com.api.apirest.model.RequestsLeaves;
import com.api.apirest.service.RequestLeavesService;

@RestController
public class RequestLeavesController {
	
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private RequestLeavesService requestService;
    
    /**
	 * Create - Add a new line request leave
	 * @param leave An object request leave
	 * @return The leave object saved
	 */
	@PostMapping("/ajoutdemandeconge")
	
	public RequestsLeaves createRequest(@RequestBody RequestsLeaves request) {
		
		return requestService.saveRequest(request);
	}
    
    

    /**
    * Read - Get all requests
    * @return - An Iterable object of requests leaves full filled
    * permet d'appeler la méthode getEmployees() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi toutes les demandes enregistrés en base de données.
    * 
    */
    @GetMapping("/demandesconges")
    public Iterable<RequestsLeaves> getRequest() {
        return requestService.getRequests();
    }
    
    /**
	 * Read - Get one line payroll 
	 * @param id The id of request
	 * @return An  request line object full filled
	 */
	@GetMapping("/demandeconge/{id}")
	public RequestsLeaves getRequest(@PathVariable("id") final Long id) {
		Optional<RequestsLeaves> requestlv = requestService.getRequest(id);
		if(requestlv.isPresent()) {
			return requestlv.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/mesdemandescongesencours/{id}/{statut}")
	public Iterable <RequestsLeaves> getMypenddings(@PathVariable("id") final int id,@PathVariable("statut") final int statut ) {
		return requestService.getMyLeavesRequestEncours(id, 0);
	
	}
	
	@GetMapping("/mesdemandescongesAnnuelsencours/{emp}/{conge}/{statut}/{annee}")
	public Iterable <RequestsLeaves> getMypenddingsAnnualleaves(@PathVariable("emp") final int emp,@PathVariable("conge") final int conge,
			@PathVariable("statut") final int statut,@PathVariable("annee") final int annee) {
		return requestService.getMyPendingAnnualRequestsLeaves(emp,conge,statut,annee);
	
	}
	
	@GetMapping("/demandesencours/{statut}")
	public Iterable <RequestsLeaves> getPenddingRequests (@PathVariable("statut")final int statut){
		
		return requestService.RequestEncours(0);
		
	}
	@GetMapping("/mesdemandesconges/{id}")
	public Iterable <RequestsLeaves> getRequests(@PathVariable("id") final int id) {
		return requestService.getRequestByEmployee(id);
		
	}
	
	
	
	 /* Update - Update an existing request line
	 * @param id - The id of payroll to update
	 * @param employee - The pay object updated
	 * @return
	*/
	@PutMapping("/majdemandeconge/{id}")
	public RequestsLeaves updateRequest(@PathVariable("id") final Long id, @RequestBody RequestsLeaves requestlv) {
		Optional<RequestsLeaves> p = requestService.getRequest(id);
		if(p.isPresent()) {
			RequestsLeaves currentrequest = p.get();
			
			Date depart = requestlv.getDepart();
			if(depart != null) {
				currentrequest.setDepart(depart);
			}
			
			Date retour =requestlv.getRetour();
			if(retour != null) {
				currentrequest.setRetour(retour);
			}
			
			int annee_demande = requestlv.getAnnee();
			if(annee_demande != 0) {
				currentrequest.setAnnee(annee_demande);
			}
			
			int solde = requestlv.getSolde();
			if(solde != 0) {
				currentrequest.setSolde(solde);
			}
			
			int statutdemande = requestlv.getStatut();
			currentrequest.setStatut(statutdemande);
			
			
			int conge_id = requestlv.getTypeconge();
			if(conge_id != 0) {
				currentrequest.setTypeconge(conge_id);
			}
			
			int employee = requestlv.getEmployee();
			if(employee != 0) {
				currentrequest.setEmployee(employee);
			}
						
			requestService.saveRequest(requestlv);
			return currentrequest;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an request
	 * @param id - The id of the request to delete
	 */
	@DeleteMapping("/deletedemande/{id}")
	public void deleteRequest(@PathVariable("id") final Long id) {
		requestService.deleteRequest(id);
	}
	
	

}
