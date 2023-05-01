package com.api.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.Grade;
import com.api.apirest.repository.GradeRepository;

import lombok.Data;

@Data
@Service

public class GradeService {
	
	@Autowired
    private GradeRepository gradeRepository;

    public Optional<Grade> getGrade(final Long id) {
        return gradeRepository.findById(id);
    }

    public Iterable<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    public void deleteGrade(final Long id) {
    	gradeRepository.deleteById(id);
    }

    public Grade saveGrade(Grade grade) {
        Grade savedGrade = gradeRepository.save(grade);
        return savedGrade;
    }

}
