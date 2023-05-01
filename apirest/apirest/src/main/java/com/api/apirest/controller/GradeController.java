package com.api.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.apirest.model.Grade;
import com.api.apirest.service.GradeService;

@RestController
public class GradeController {
	/**permet d’appeler les méthodes pour communiquer avec la base de données.
	 * 
	 */
    @Autowired
    private GradeService gradeService;
    
    /**
	 * Create - Add a new grade
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/grade")
	
	public Grade createGrade(@RequestBody Grade grade) {
		return gradeService.saveGrade(grade);
	}
    
    

    /**
    * Read - Get all grades
    * @return - An Iterable object of Grade full filled
    * permet d'appeler la méthode getGrades() du service, 
    * ce dernier appellera la méthode findAll() du repository, 
    * et nous obtiendrons ainsi toutes les Grades enregistrées en base de données.
    * 
    */
    @GetMapping("/grades")
    public Iterable<Grade> getGrades() {
        return gradeService.getGrades();
    }
    
    /**
	 * Read - Get one function
	 * @param id The id of the grade
	 * @return An function object full filled
	 */
	@GetMapping("/grade/{id}")
	public Grade getGrade(@PathVariable("id") final Long id) {
		Optional<Grade> grade = gradeService.getGrade(id);
		if(grade.isPresent()) {
			return grade.get();
		} else {
			return null;
		}
	}
	
	 /* Update - Update an existing grade
	 * @param id - The id of the grade to update
	 * @param employee - The grade object updated
	 * @return
	 */
	@PutMapping("/majgrade/{id}")
	public Grade updateGrade(@PathVariable("id") final Long id, @RequestBody Grade grade) {
		Optional<Grade> g = gradeService.getGrade(id);
		if(g.isPresent()) {
			Grade currentGrade = g.get();
			
			String gradeM = grade.getGrade();
			if(gradeM != null) {
				currentGrade.setGrade(gradeM);
			}
			
			int habilit = grade.getHabilitation();
			if(habilit != 0) {
				currentGrade.setHabilitation(habilit);
			}
					
			gradeService.saveGrade(grade);
			return currentGrade;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an grade
	 * @param id - The id of the grade to delete
	 */
	@DeleteMapping("/deletegrade/{id}")
	public void deleteGrade(@PathVariable("id") final Long id) {
		gradeService.deleteGrade(id);
	}
	

}
