package com.api.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.Primes;
import com.api.apirest.repository.PrimesRepository;

import lombok.Data;

@Data
@Service

public class PrimesService {

	@Autowired
    private PrimesRepository primeRepository;

    public Optional<Primes> getPrime(final Long id) {
        return primeRepository.findById(id);
    }

    public Iterable<Primes> getPrimes() {
        return primeRepository.findAll();
    }

    public void deletePrime(final Long id) {
    	primeRepository.deleteById(id);
    }

    public Primes savePrime(Primes prime) {
    	Primes savedPrime = primeRepository.save(prime);
        return savedPrime;
    }
}
