package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Grade;
import com.websevice.gepers.repository.GradeProxy;

import lombok.Data;

@Data
@Service

public class GradeService {
	@Autowired
	private GradeProxy gradeProxy;
	
	public Grade getGrade(final int id) {
		return gradeProxy.getGrade(id);
	}
	
	public Iterable<Grade> getGrades() {
		return gradeProxy.getGrades();
	}
	
	public void deleteGrade(final int id) {
		gradeProxy.deleteGrade(id);
	}
	
	public Grade saveGrade(Grade grade) {
		Grade savedGrade;
		
		if(grade.getId() == null) {
			// If id is null, then it is a new employee.
			savedGrade = gradeProxy.createGrade(grade);
		} else {
			savedGrade = gradeProxy.updateGrade(grade);
		}
		
		return savedGrade;
	}

}
