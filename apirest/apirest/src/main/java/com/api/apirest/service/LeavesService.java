package com.api.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.Leaves;
import com.api.apirest.repository.LeavesRepository;

import lombok.Data;

@Data
@Service

public class LeavesService {
	
	@Autowired
    private LeavesRepository leaveRepository;

    public Optional<Leaves> getLeaves(final Long id) {
        return leaveRepository.findById(id);
    }

    public Iterable<Leaves> getLeaves() {
        return leaveRepository.findAll();
    }

    public void deleteLeave(final Long id) {
    	leaveRepository.deleteById(id);
    }

    public Leaves saveLeave(Leaves leave) {
    	Leaves savedLeave = leaveRepository.save(leave);
        return savedLeave;
    }

}
