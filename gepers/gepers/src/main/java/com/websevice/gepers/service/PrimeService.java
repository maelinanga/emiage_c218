package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Primes;
import com.websevice.gepers.repository.PrimeProxy;

import lombok.Data;

@Data
@Service

public class PrimeService {
	
	@Autowired
	private PrimeProxy primeProxy;
	
	public Primes getPrime(final int id) {
		return primeProxy.getPrime(id);
	}
	
	public Iterable<Primes> getPrimes() {
		return primeProxy.getPrimes();
	}
	
	public void deletePrime(final int id) {
		primeProxy.deletePrime(id);
	}
	
	public Primes savePrime(Primes prime) {
		Primes savedprime;
		
		if(prime.getId() == null) {
			// If id is null, then it is a new employee.
			savedprime = primeProxy.createPrime(prime);
		} else {
			savedprime = primeProxy.updatePrime(prime);
		}
		
		return savedprime;
	}


}
