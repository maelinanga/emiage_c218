package com.api.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.api.apirest.model.Fonction;
import com.api.apirest.repository.FonctionRepository;

import lombok.Data;

@Data
@Service
public class FonctionService {
	
	@Autowired
    private FonctionRepository fonctionRepository;

    public Optional<Fonction> getFonction(final Long id) {
        return fonctionRepository.findById(id);
    }

    public Iterable<Fonction> getFonctions() {
        return fonctionRepository.findAll();
    }

    public void deleteFonction(final Long id) {
    	fonctionRepository.deleteById(id);
    }

    public Fonction saveFonction(Fonction fonction) {
    	Fonction savedFonction = fonctionRepository.save(fonction);
        return savedFonction;
    }

}
