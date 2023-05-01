package com.websevice.gepers.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Leaves;
import com.websevice.gepers.repository.LeavesProxy;

import lombok.Data;

@Data
@Service

public class LeavesService {
	
	@Autowired
	private LeavesProxy leavesProxy;
	
	public Leaves getLeave(final int id) {
		return leavesProxy.getLeave(id);
	}
	
	public Iterable<Leaves> getLeaves() {
		return leavesProxy.getLeaves();
	}
	
	public void deleteLeave(final int id) {
		leavesProxy.deleteLeave(id);
	}
	
	public Leaves saveLeave(Leaves leaves) {
		Leaves savedleave;
		
		if(leaves.getId() == null) {
			// If id is null, then it is a new employee.
			savedleave = leavesProxy.createLeave(leaves);
		} else {
			savedleave = leavesProxy.updateLeave(leaves);
		}
		
		return savedleave;
	}

}
