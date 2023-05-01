package com.api.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Fonction;

@Repository
public interface FonctionRepository extends CrudRepository<Fonction, Long>{

}
