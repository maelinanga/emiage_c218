package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Retenues;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RetenueProxy {
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Retenues> getRetenues() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getRetenueUrl = baseApiUrl + "/retenues";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Retenues>> response = restTemplate.exchange(
        		getRetenueUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Retenues>>() {}
                );
        
        log.debug("Get Retenues call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Retenues createRetenue(Retenues g) {
        String baseApiUrl = props.getApiUrl();
        String createRetenuesUrl = baseApiUrl + "/retenue";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Retenues> request = new HttpEntity<Retenues>(g);
        ResponseEntity<Retenues> response = restTemplate.exchange(
        	createRetenuesUrl,
            HttpMethod.POST,
            request,
            Retenues.class);

        log.debug("Create Retenues call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the retenue
	 * @return The grade which matches the id
	 */
	public Retenues getRetenue(int id) {
		String baseApiUrl = props.getApiUrl();
		String getRetenueUrl = baseApiUrl + "/retenue/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Retenues> response = restTemplate.exchange(
				getRetenueUrl, 
				HttpMethod.GET, 
				null,
				Retenues.class
			);
		
		log.debug("Get Retenue call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deleteRetenue(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteRetenueUrl = baseApiUrl + "/deleteretenue/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteRetenueUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete retenue call " + response.getStatusCode().toString());
	}

	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public Retenues updateRetenue(Retenues e) {
		String baseApiUrl = props.getApiUrl();
		String updateRetenueUrl = baseApiUrl + "/majretenue/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Retenues> request = new HttpEntity<Retenues>(e);
		ResponseEntity<Retenues> response = restTemplate.exchange(
				updateRetenueUrl, 
				HttpMethod.PUT, 
				request, 
				Retenues.class);
		
		log.debug("Update grade retenue " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
