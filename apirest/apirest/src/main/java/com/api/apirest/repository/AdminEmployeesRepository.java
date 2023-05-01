package com.api.apirest.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.AdminEmployees;


public interface AdminEmployeesRepository extends CrudRepository<AdminEmployees, Long> {

}
