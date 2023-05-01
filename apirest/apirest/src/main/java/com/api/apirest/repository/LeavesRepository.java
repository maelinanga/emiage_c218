package com.api.apirest.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Leaves;

@Repository
public interface LeavesRepository extends CrudRepository<Leaves, Long> {

}
