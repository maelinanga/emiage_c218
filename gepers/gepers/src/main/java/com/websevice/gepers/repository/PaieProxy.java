package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Paie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class PaieProxy {
	
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Paie> getPaies() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getPaieUrl = baseApiUrl + "/paies";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Paie>> response = restTemplate.exchange(
        		getPaieUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Paie>>() {}
                );
        
        log.debug("Get Grade call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Paie createPaie(Paie g) {
        String baseApiUrl = props.getApiUrl();
        String createPaieUrl = baseApiUrl + "/paie";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Paie> request = new HttpEntity<Paie>(g);
        ResponseEntity<Paie> response = restTemplate.exchange(
        	createPaieUrl,
            HttpMethod.POST,
            request,
            Paie.class);

        log.debug("Create Paie call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the grade
	 * @return The grade which matches the id
	 */
	public Paie getPaie(int id) {
		String baseApiUrl = props.getApiUrl();
		String getPaieUrl = baseApiUrl + "/paie/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Paie> response = restTemplate.exchange(
				getPaieUrl, 
				HttpMethod.GET, 
				null,
				Paie.class
			);
		
		log.debug("Get Paie call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deletePaie(int id) {
		String baseApiUrl = props.getApiUrl();
		String deletePaieUrl = baseApiUrl + "/deletepaie/" + id;
		
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
	public Paie updatePaie(Paie e) {
		String baseApiUrl = props.getApiUrl();
		String updatePaieUrl = baseApiUrl + "/majpaie/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Paie> request = new HttpEntity<Paie>(e);
		ResponseEntity<Paie> response = restTemplate.exchange(
				updatePaieUrl, 
				HttpMethod.PUT, 
				request, 
				Paie.class);
		
		log.debug("Update Paie call " + response.getStatusCode().toString());
		
		return response.getBody();
	}



}
