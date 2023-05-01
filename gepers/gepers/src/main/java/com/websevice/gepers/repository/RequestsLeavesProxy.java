package com.websevice.gepers.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.RequestsLeaves;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class RequestsLeavesProxy {
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all requests
    */

    public Iterable<RequestsLeaves> getRequests() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getRequestUrl = baseApiUrl + "/demandesconges";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<RequestsLeaves>> response = restTemplate.exchange(
        		getRequestUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<RequestsLeaves>>() {}
                );
        
        log.debug("Get RequestsLeaves call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public RequestsLeaves createRequest(RequestsLeaves g) {
        String baseApiUrl = props.getApiUrl();
        String createRequestUrl = baseApiUrl + "/ajoutdemandeconge";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestsLeaves> request = new HttpEntity<RequestsLeaves>(g);
        ResponseEntity<RequestsLeaves> response = restTemplate.exchange(
        	createRequestUrl,
            HttpMethod.POST,
            request,
            RequestsLeaves.class);

        log.debug("Create Paie call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an request by the id
	 * @param id The id of the grade
	 * @return The grade which matches the id
	 */
	public RequestsLeaves getRequest(int id) {
		String baseApiUrl = props.getApiUrl();
		String getRequestUrl = baseApiUrl + "/demandeconge/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RequestsLeaves> response = restTemplate.exchange(
				getRequestUrl, 
				HttpMethod.GET, 
				null,
				RequestsLeaves.class
			);
		
		log.debug("Get Requestleave call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	
	 /**
		 * Get an request by the employee
		 * @param id The id of the grade
		 * @return The grade which matches the id
		 */
		public Iterable <RequestsLeaves> getRequestByEmployee(int id) {
			String baseApiUrl = props.getApiUrl();
			String getRequestUrl = baseApiUrl + "/mesdemandesconges/" + id;

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity <Iterable<RequestsLeaves>> response = restTemplate.exchange(
					 getRequestUrl, 
				     HttpMethod.GET, 
				     null,
				     new ParameterizedTypeReference<Iterable<RequestsLeaves>>() {}
					
				);
			
			log.debug("Get requests call " + response.getStatusCode().toString());
			
			return response.getBody();
		}
	/*
	 * mes demandes en cours
	 */
		public Iterable<RequestsLeaves> getMyRequestPendding(int em, int statut) {
			String baseApiUrl = props.getApiUrl();
			String getRequestUrl = baseApiUrl + "/mesdemandescongesencours/" + em + "/" + statut;

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Iterable<RequestsLeaves>> response = restTemplate.exchange(
					 getRequestUrl, 
				      HttpMethod.GET, 
				       null,
				       new ParameterizedTypeReference<Iterable<RequestsLeaves>>() {}
				);
			
			log.debug("Get requests call " + response.getStatusCode().toString());
			
			return response.getBody();
		}
		
		/*
		 * toutes les demandes en cours
		 */
			public Iterable<RequestsLeaves> getRequestsPenddings(int statut) {
				String baseApiUrl = props.getApiUrl();
				String getRequestUrl = baseApiUrl + "/demandesencours/" + statut;

				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<Iterable<RequestsLeaves>> response = restTemplate.exchange(
						getRequestUrl, 
					    HttpMethod.GET, 
					    null,
					    new ParameterizedTypeReference<Iterable<RequestsLeaves>>() {}
					);
				
				log.debug("Get requests call " + response.getStatusCode().toString());
				
				return response.getBody();
			}
	
	
	/**
	 * Delete an request
	 * .
	 * @param id of request to delete
	 */
	public void deleteRequest(int id) {
		String baseApiUrl = props.getApiUrl();
		String deletePaieUrl = baseApiUrl + "/deletedemande/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deletePaieUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Paie call " + response.getStatusCode().toString());
	}

	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public RequestsLeaves updateRequest(RequestsLeaves e) {
		String baseApiUrl = props.getApiUrl();
		String updateRequestUrl = baseApiUrl + "/majdemandeconge/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<RequestsLeaves> request = new HttpEntity<RequestsLeaves>(e);
		ResponseEntity<RequestsLeaves> response = restTemplate.exchange(
				updateRequestUrl, 
				HttpMethod.PUT, 
				request, 
				RequestsLeaves.class);
		
		log.debug("Update Requests call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
