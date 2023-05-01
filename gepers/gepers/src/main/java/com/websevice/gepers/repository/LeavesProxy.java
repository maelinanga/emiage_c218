package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Leaves;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class LeavesProxy {
	
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Leaves> getLeaves() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getLeaveUrl = baseApiUrl + "/conges";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Leaves>> response = restTemplate.exchange(
        		getLeaveUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Leaves>>() {}
                );
        
        log.debug("Get Grade call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Leaves createLeave(Leaves g) {
        String baseApiUrl = props.getApiUrl();
        String createLeaveUrl = baseApiUrl + "/conge";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Leaves> request = new HttpEntity<Leaves>(g);
        ResponseEntity<Leaves> response = restTemplate.exchange(
        	createLeaveUrl,
            HttpMethod.POST,
            request,
            Leaves.class);

        log.debug("Create leave call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an leave by the id
	 * @param id The id of the grade
	 * @return The grade which matches the id
	 */
	public Leaves getLeave(int id) {
		String baseApiUrl = props.getApiUrl();
		String getLeaveUrl = baseApiUrl + "/conge/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Leaves> response = restTemplate.exchange(
				getLeaveUrl, 
				HttpMethod.GET, 
				null,
				Leaves.class
			);
		
		log.debug("Get leave call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an leave using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deleteLeave(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteLeaveUrl = baseApiUrl + "/deleteconge/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteLeaveUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete leave call " + response.getStatusCode().toString());
	}

	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public Leaves updateLeave(Leaves e) {
		String baseApiUrl = props.getApiUrl();
		String updateLeaveUrl = baseApiUrl + "/majconge/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Leaves> request = new HttpEntity<Leaves>(e);
		ResponseEntity<Leaves> response = restTemplate.exchange(
				updateLeaveUrl, 
				HttpMethod.PUT, 
				request, 
				Leaves.class);
		
		log.debug("Update Leaves call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
