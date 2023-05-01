package com.api.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Primes;

@Repository
public interface PrimesRepository extends CrudRepository<Primes, Long>{

}
