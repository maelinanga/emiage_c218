package com.api.apirest.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.apirest.model.AdminEmployees;

import com.api.apirest.repository.AdminEmployeesRepository;
import com.api.apirest.repository.EmployeeRepository;

import lombok.Data;

@Data
@Service

public class AdminEmployeeService {
	
	@Autowired
    private AdminEmployeesRepository adminRepository;

    public Optional<AdminEmployees> getAdminEmployee(final Long id) {
        return adminRepository.findById(id);
    }

    public Iterable<AdminEmployees> getAdminEmployees() {
        return adminRepository.findAll();
    }

    public void deleteAdminEmployee(final Long id) {
    	adminRepository.deleteById(id);
    }

    public AdminEmployees saveAdminEmployee(AdminEmployees adminemployee) {
    	AdminEmployees savedAdminEmployee = adminRepository.save(adminemployee);
        return savedAdminEmployee;
    }


}
