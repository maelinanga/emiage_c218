package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Fonction;
import com.websevice.gepers.repository.FonctionProxy;

import lombok.Data;

@Data
@Service

public class FonctionService {
	
	@Autowired
	private FonctionProxy fonctionProxy;
	
	public Fonction getFonction(final int id) {
		return fonctionProxy.getFonction(id);
	}
	
	public Iterable<Fonction> getFonctions() {
		return fonctionProxy.getFonctions();
	}
	
	public void deleteFonction(final int id) {
		fonctionProxy.deleteFonction(id);
	}
	
	public Fonction saveFonction(Fonction fonction) {
		Fonction savedFonction;
		
		if(fonction.getId() == null) {
			// If id is null, then it is a new function.
			savedFonction = fonctionProxy.createFonction(fonction);
		} else {
			savedFonction = fonctionProxy.updateFonction(fonction);
		}
		
		return savedFonction;
	}

}
