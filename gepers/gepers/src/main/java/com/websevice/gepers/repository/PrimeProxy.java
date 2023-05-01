package com.websevice.gepers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.websevice.gepers.CustomProperties;
import com.websevice.gepers.modele.Primes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component

public class PrimeProxy {
	
	@Autowired
    private CustomProperties props;

    /**
    * Get all grades
    * @return An iterable of all grades
    */

    public Iterable<Primes> getPrimes() {
    	//grâce à notre objet CustomProperties, on récupère l’URL de l’API
    	
        String baseApiUrl = props.getApiUrl();
        
        //on complète l’URL de l’API par le path de l'endpoint à joindre.
        
        String getPrimeUrl = baseApiUrl + "/primes";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Primes>> response = restTemplate.exchange(
        		getPrimeUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Primes>>() {}
                );
        
        log.debug("Get Grade call " + response.getStatusCode().toString());
        
        //on récupère notre objet Iterable<grade> grâce à la méthode getBody()de l’objet Response
        
        return response.getBody();
    }

    public Primes createPrime(Primes g) {
        String baseApiUrl = props.getApiUrl();
        String createPrimesUrl = baseApiUrl + "/prime";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Primes> request = new HttpEntity<Primes>(g);
        ResponseEntity<Primes> response = restTemplate.exchange(
        	createPrimesUrl,
            HttpMethod.POST,
            request,
            Primes.class);

        log.debug("Create Prime call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
	 * Get an grade by the id
	 * @param id The id of the prime
	 * @return The grade which matches the id
	 */
	public Primes getPrime(int id) {
		String baseApiUrl = props.getApiUrl();
		String getPrimesUrl = baseApiUrl + "/prime/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Primes> response = restTemplate.exchange(
				getPrimesUrl, 
				HttpMethod.GET, 
				null,
				Primes.class
			);
		
		log.debug("Get Prime call " + response.getStatusCode().toString());
		
		return response.getBody();
	}

	/**
	 * Delete an grade using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The grade to delete
	 */
	public void deletePrime(int id) {
		String baseApiUrl = props.getApiUrl();
		String deletePrimeUrl = baseApiUrl + "/deleteprime/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deletePrimeUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete prime call " + response.getStatusCode().toString());
	}

	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing grade to update
	 */
	public Primes updatePrime(Primes e) {
		String baseApiUrl = props.getApiUrl();
		String updatePrimeUrl = baseApiUrl + "/majprime/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Primes> request = new HttpEntity<Primes>(e);
		ResponseEntity<Primes> response = restTemplate.exchange(
				updatePrimeUrl, 
				HttpMethod.PUT, 
				request, 
				Primes.class);
		
		log.debug("Update grade call " + response.getStatusCode().toString());
		
		return response.getBody();
	}


}
