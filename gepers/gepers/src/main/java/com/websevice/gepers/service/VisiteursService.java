package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Visiteurs;
import com.websevice.gepers.repository.VisiteursProxy;

import lombok.Data;

@Data
@Service

public class VisiteursService {
	
	@Autowired
	private VisiteursProxy visiteurProxy;
	
	public Visiteurs getVisiteur(final int id) {
		return visiteurProxy.getVisiteur(id);
	}
	
	public Iterable<Visiteurs> getVisiteurs() {
		return visiteurProxy.getVisiteurs();
	}
	
	public void deleteVisiteur(final int id) {
		visiteurProxy.deleteVisiteur(id);
	}
	
	public Visiteurs saveVisiteur(Visiteurs visiteur) {
		Visiteurs savedVisiteur;
		savedVisiteur = visiteurProxy.createVisiteur(visiteur);
		return savedVisiteur;
		
		/**if(visiteur.getId() == null) {
			// If id is null, then it is a new visiteur.
			savedVisiteur = visiteurProxy.createVisiteur(visiteur);
		} else {
			savedVisiteur = visiteurProxy.updateVisiteur(visiteur);
		}*/
		
		
	}

}
