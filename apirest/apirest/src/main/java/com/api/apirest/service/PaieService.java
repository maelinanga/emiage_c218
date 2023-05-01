package com.api.apirest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.model.Paie;
import com.api.apirest.repository.PaieRepository;

import lombok.Data;

@Data
@Service
public class PaieService {
	
	@Autowired
    private PaieRepository paieRepository;

    public Optional<Paie> getPaie(final Long id) {
        return paieRepository.findById(id);
    }

    public Iterable<Paie> getPaies() {
        return paieRepository.findAll();
    }

    public void deletePaie(final Long id) {
    	paieRepository.deleteById(id);
    }

    public Paie savePaie(Paie paie) {
    	Paie savedPaie = paieRepository.save(paie);
        return savedPaie;
    }
    
    public Iterable <Paie> findByEmployee_Id(Integer employee) {
       return paieRepository.findByEmployee_Id(employee);
    }
    
    
  
    public Iterable <Paie> findByMonthAndYear(Integer mois, Integer annee) {
        ArrayList<Paie> fiche_p = new ArrayList<>();
        paieRepository.findByMoisAndAnnee(mois, annee).forEach(fiche_p::add);
        return fiche_p;
    }

  
    public Optional <Paie> findByEmployee_IdAndMonthAndYear(Integer employee, Integer mois, Integer annee) {
    	return paieRepository.findByEmployee_IdAndMoisAndAnnee(employee, mois, annee);
    }
    

    
    
}
