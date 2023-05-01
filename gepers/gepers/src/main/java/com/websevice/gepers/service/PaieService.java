package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Paie;
import com.websevice.gepers.repository.PaieProxy;

import lombok.Data;

@Data
@Service
public class PaieService {
	
	@Autowired
	private PaieProxy paieProxy;
	
	public Paie getPaie(final int id) {
		return paieProxy.getPaie(id);
	}
	
	public Iterable<Paie> getPaies() {
		return paieProxy.getPaies();
	}
	
	public void deletePaie(final int id) {
		paieProxy.deletePaie(id);;
	}
	
	public Paie savePaie(Paie paie) {
		Paie savedpaie;
		
		if(paie.getId() == null) {
			// If id is null, then it is a new retenue.
			savedpaie = paieProxy.createPaie(paie);
		} else {
			savedpaie = paieProxy.updatePaie(paie);
		}
		
		return savedpaie;
	}

}
