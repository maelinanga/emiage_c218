package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.AdminEmployees;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class AdminEmployeesProxy {
	
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all requests
    */

    public Iterable<AdminEmployees> getAdmins() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getAdminUrl = baseApiUrl + "/adminemployees";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<AdminEmployees>> response = restTemplate.exchange(
        		getAdminUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<AdminEmployees>>() {}
                );
        
        log.debug("Get AdminEmployee call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public AdminEmployees createAdmin(AdminEmployees g) {
        String baseApiUrl = props.getApiUrl();
        String createAdminUrl = baseApiUrl + "/adminemployee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AdminEmployees> request = new HttpEntity<AdminEmployees>(g);
        ResponseEntity<AdminEmployees> response = restTemplate.exchange(
        	createAdminUrl,
            HttpMethod.POST,
            request,
            AdminEmployees.class);

        log.debug("Create AdminEmployee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the grade
	 * @return The grade which matches the id
	 */
	public AdminEmployees getAdmin(int id) {
		String baseApiUrl = props.getApiUrl();
		String getAdmintUrl = baseApiUrl + "/adminemployee/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AdminEmployees> response = restTemplate.exchange(
				getAdmintUrl, 
				HttpMethod.GET, 
				null,
				AdminEmployees.class
			);
		
		log.debug("Get AdminEmployee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deleteAdmin(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteAdminUrl = baseApiUrl + "/deleteadminemployee/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteAdminUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete AdminEmployee call " + response.getStatusCode().toString());
	}

	/**
	 * Update an Adminemployee - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public AdminEmployees updateAdmin(AdminEmployees e) {
		String baseApiUrl = props.getApiUrl();
		String updateAdminUrl = baseApiUrl + "/majadminemployee/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<AdminEmployees> request = new HttpEntity<AdminEmployees>(e);
		ResponseEntity<AdminEmployees> response = restTemplate.exchange(
				updateAdminUrl, 
				HttpMethod.PUT, 
				request, 
				AdminEmployees.class);
		
		log.debug("Update AdminEmployee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
