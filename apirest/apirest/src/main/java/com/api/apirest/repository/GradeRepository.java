package com.api.apirest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long>{

}
