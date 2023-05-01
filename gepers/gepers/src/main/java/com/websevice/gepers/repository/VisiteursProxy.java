package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Visiteurs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class VisiteursProxy {
	
	@Autowired
    private CustomProperties proper;
	
	
	 /**
	    * Get all visiteurs
	    * @return An iterable of all visiteurs
	    */

	    public Iterable<Visiteurs> getVisiteurs() {
	    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
	    	
	        String baseApiUrl = proper.getApiUrl();
	        
	        //on complète l’URL de l’API par le path de l'endpoint à joindre.
	        
	        String getVisiteursUrl = baseApiUrl + "/Visiteurs";
	        
	        /*
	         * n appelle la méthode exchange en transmettant :l’URL ;

				la méthode HTTP (grâce à l’enum HttpMethod) ;

				Null en lieu et place d’un objet HttpEntity, ainsi on laisse un comportement par défaut ;

				le type retour, ici je suis obligé d’utiliser un objet ParameterizedTypeReference car /visiturss 
				renvoie un objet Iterable<Employee>. Mais si l’endpoint renvoie un objet simple, 
				alors il suffira d’indiquer <Object>.class.
	         */

	        RestTemplate restTemplate2 = new RestTemplate();
	        ResponseEntity<Iterable<Visiteurs>> response = restTemplate2.exchange(
	                getVisiteursUrl,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<Iterable<Visiteurs>>() {}
	                );
	        
	        log.debug("Get Visiteurs call " + response.getStatusCode().toString());
	        
	        //on récupère notre objet Iterable<Employee> grâce à la méthode getBody() de l’objet Response
	        
	        return response.getBody();
	    }
	    
	    /**
	     * nouveau visiteur
	     * @param visiteur
	     * @return
	     */
	    public Visiteurs createVisiteur(Visiteurs visiteur) {
	    	
	        String baseApiUrl = proper.getApiUrl();
	        
	        String createVisiteurUrl = baseApiUrl + "/creervisiteur";

	        RestTemplate restTemplate2 = new RestTemplate();
	        HttpEntity<Visiteurs> request = new HttpEntity<Visiteurs>(visiteur);
	        ResponseEntity<Visiteurs> response = restTemplate2.exchange(
	        	createVisiteurUrl,
	            HttpMethod.POST,
	            request,
	            Visiteurs.class);

	        log.debug("Create Visiteurs call " + response.getStatusCode().toString());

	        return response.getBody();
	    }

	    /**
		 * Get an employee by the id
		 * @param id The id of the employee
		 * @return The employee which matches the id
		 */
		public Visiteurs getVisiteur(int id) {
			String baseApiUrl = proper.getApiUrl();
			String getVisiteurUrl = baseApiUrl + "/visiteur/" + id;

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Visiteurs> response = restTemplate.exchange(
					getVisiteurUrl, 
					HttpMethod.GET, 
					null,
					Visiteurs.class
				);
			
			log.debug("Get Visiteurs call " + response.getStatusCode().toString());
			
			return response.getBody();
		}

	
		/**
		 * Delete an visiteur using exchange method of RestTemplate
		 * instead of delete method in order to log the response status code.
		 * @param e The employee to delete
		 */
		public void deleteVisiteur(int id) {
			String baseApiUrl = proper.getApiUrl();
			String deleteVisiteurUrl = baseApiUrl + "/visiteur/" + id;
			
			RestTemplate restTemplate2 = new RestTemplate();
			ResponseEntity<Void> response = restTemplate2.exchange(
					deleteVisiteurUrl, 
					HttpMethod.DELETE, 
					null, 
					Void.class);
			
			log.debug("Delete visiteur call " + response.getStatusCode().toString());
		}
		
		/**
		 * Update an employee - using the PUT HTTP Method.
		 * @param e Existing employee to update
		 */
		public Visiteurs updateVisiteur(Visiteurs v) {
			String baseApiUrl = proper.getApiUrl();
			String updatevisiteurUrl = baseApiUrl + "/visiteur/" + v.getId();

			RestTemplate restTemplate2 = new RestTemplate();
			HttpEntity<Visiteurs> request = new HttpEntity<Visiteurs>(v);
			ResponseEntity<Visiteurs> response = restTemplate2.exchange(
					updatevisiteurUrl, 
					HttpMethod.PUT, 
					request, 
					Visiteurs.class);
			
			log.debug("Update visiteur call " + response.getStatusCode().toString());
			
			return response.getBody();
		}

}
