package com.api.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.Retenues;
import com.api.apirest.repository.RetenuesRepository;

import lombok.Data;

@Data
@Service

public class RetenuesService {
	
	@Autowired
    private RetenuesRepository retenueRepository;

    public Optional<Retenues> getRetenue(final Long id) {
        return retenueRepository.findById(id);
    }

    public Iterable<Retenues> getRetenues() {
        return retenueRepository.findAll();
    }

    public void deleteRetenue(final Long id) {
    	retenueRepository.deleteById(id);
    }

    public Retenues saveRetenue(Retenues retenue) {
    	Retenues savedRetenue = retenueRepository.save(retenue);
        return savedRetenue ;
    }

}
