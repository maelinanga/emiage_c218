package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Grade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GradeProxy {
	
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Grade> getGrades() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getGradeUrl = baseApiUrl + "/grades";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Grade>> response = restTemplate.exchange(
        		getGradeUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Grade>>() {}
                );
        
        log.debug("Get Grade call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Grade createGrade(Grade g) {
        String baseApiUrl = props.getApiUrl();
        String createGradeUrl = baseApiUrl + "/grade";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Grade> request = new HttpEntity<Grade>(g);
        ResponseEntity<Grade> response = restTemplate.exchange(
        	createGradeUrl,
            HttpMethod.POST,
            request,
            Grade.class);

        log.debug("Create Grade call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the grade
	 * @return The grade which matches the id
	 */
	public Grade getGrade(int id) {
		String baseApiUrl = props.getApiUrl();
		String getGradeUrl = baseApiUrl + "/grade/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Grade> response = restTemplate.exchange(
				getGradeUrl, 
				HttpMethod.GET, 
				null,
				Grade.class
			);
		
		log.debug("Get Grade call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deleteGrade(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteGradeUrl = baseApiUrl + "/deletegrade/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteGradeUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Grade call " + response.getStatusCode().toString());
	}
	
		
	

	/**
	 * Update an grade - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public Grade updateGrade(Grade g) {
		String baseApiUrl = props.getApiUrl();
		String updateGradeUrl = baseApiUrl + "/majgrade/" + g.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Grade> request = new HttpEntity<Grade>(g);
		ResponseEntity<Grade> response = restTemplate.exchange(
				updateGradeUrl, 
				HttpMethod.PUT, 
				request, 
				Grade.class);
		
		log.debug("Update grade call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
