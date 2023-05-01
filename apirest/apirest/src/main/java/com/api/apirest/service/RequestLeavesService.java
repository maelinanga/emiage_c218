package com.api.apirest.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.RequestsLeaves;
import com.api.apirest.repository.RequestLeavesRepository;

import lombok.Data;

@Data
@Service
public class RequestLeavesService {
	
	@Autowired
    private RequestLeavesRepository requestsRepository;

    public Optional<RequestsLeaves> getRequest(final Long id) {
        return requestsRepository.findById(id);
    }
    
   public Iterable <RequestsLeaves> getRequestByEmployee(final int employee_id) {
        return requestsRepository.findByEmployee(employee_id);
    }

    public Iterable <RequestsLeaves> getMyLeavesRequestEncours(final int id, final int statut) {
    	
    	return requestsRepository.findByEmployeeAndStatut(id, statut);
    }
    
    public Iterable <RequestsLeaves> RequestEncours(final int statut) {
    	
    	return requestsRepository.findByStatut(statut);
    }
    
    public Long getCountLeavesEnCours(final int statut){
    	
    	return requestsRepository.countByStatut(statut);
    }
   
  
    /*
     * Cette réponse renvoie une liste de toutes les entités Employés dont le champ vacationEndDate est antérieur à la date actuelle, ce qui indique que leurs vacances sont terminées.
     */
    public Iterable <RequestsLeaves> finishedVacation (){
	  
	  return requestsRepository.findByRetourBefore(LocalDate.now());
  }
    
    public Iterable<RequestsLeaves> getRequests() {
        return requestsRepository.findAll();
    }

    public void deleteRequest(final Long id) {
    	requestsRepository.deleteById(id);
    }

    public RequestsLeaves saveRequest(RequestsLeaves request) {
    	RequestsLeaves savedRequest = requestsRepository.save(request);
        return savedRequest;
    }

}
