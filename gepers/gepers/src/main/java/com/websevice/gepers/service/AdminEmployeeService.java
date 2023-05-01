package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.AdminEmployees;
import com.websevice.gepers.repository.AdminEmployeesProxy;

import lombok.Data;

@Data
@Service
public class AdminEmployeeService {
	
	@Autowired
	private AdminEmployeesProxy adminProxy;
	
	public AdminEmployees getAdminEmployee(final int id) {
		return adminProxy.getAdmin(id);
	}
	
	public Iterable<AdminEmployees> getAdminEmployees() {
		return adminProxy.getAdmins();
	}
	
	public void deleteAdminEmployee(final int id) {
		adminProxy.deleteAdmin(id);
	}
	
	public AdminEmployees saveAdminEmployee(AdminEmployees adminempl) {
		AdminEmployees savedAdmin;
		

		if(adminempl.getId() == null) {
			// If id is null, then it is a new employee.
			savedAdmin = adminProxy.createAdmin(adminempl);
		} else {
			savedAdmin = adminProxy.updateAdmin(adminempl);
		}
		
		return savedAdmin;
	}

}
