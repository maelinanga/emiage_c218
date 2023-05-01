package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Fonction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FonctionProxy {
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Fonction> getFonctions() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getFonctionUrl = baseApiUrl + "/fonctions";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Fonction>> response = restTemplate.exchange(
        		getFonctionUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Fonction>>() {}
                );
        
        log.debug("Get fonction call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Fonction createFonction(Fonction f) {
        String baseApiUrl = props.getApiUrl();
        String createFonctionUrl = baseApiUrl + "/fonction";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Fonction> request = new HttpEntity<Fonction>(f);
        ResponseEntity<Fonction> response = restTemplate.exchange(
        	createFonctionUrl,
            HttpMethod.POST,
            request,
            Fonction.class);

        log.debug("Create Fonction call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the fonction
	 * @return The grade which matches the id
	 */
	public Fonction getFonction(int id) {
		String baseApiUrl = props.getApiUrl();
		String getFonctionUrl = baseApiUrl + "/fonction/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Fonction> response = restTemplate.exchange(
				getFonctionUrl, 
				HttpMethod.GET, 
				null,
				Fonction.class
			);
		
		log.debug("Get Fonction call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deleteFonction(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteFonctionUrl = baseApiUrl + "/deletefonction/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteFonctionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Fonction call " + response.getStatusCode().toString());
	}

	/**
	 * Update an fonction - using the PUT HTTP Method.
	 * @param e Existing fonction to update
	 */
	public Fonction updateFonction(Fonction e) {
		String baseApiUrl = props.getApiUrl();
		String updateFonctionUrl = baseApiUrl + "/majfonction/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Fonction> request = new HttpEntity<Fonction>(e);
		ResponseEntity<Fonction> response = restTemplate.exchange(
				updateFonctionUrl, 
				HttpMethod.PUT, 
				request, 
				Fonction.class);
		
		log.debug("Update fonction call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
