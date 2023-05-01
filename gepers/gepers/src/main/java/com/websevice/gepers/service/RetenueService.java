package com.websevice.gepers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.Retenues;
import com.websevice.gepers.repository.RetenueProxy;

import lombok.Data;

@Data
@Service


public class RetenueService {
	
	@Autowired
	private RetenueProxy retenueProxy;
	
	public Retenues getRetenue(final int id) {
		return retenueProxy.getRetenue(id);
	}
	
	public Iterable<Retenues> getRetenues() {
		return retenueProxy.getRetenues();
	}
	
	public void deleteRtenue(final int id) {
		retenueProxy.deleteRetenue(id);
	}
	
	public Retenues saveRetenue(Retenues retenue) {
		Retenues savedretenue;
		
		if(retenue.getId() == null) {
			// If id is null, then it is a new retenue.
			savedretenue = retenueProxy.createRetenue(retenue);
		} else {
			savedretenue = retenueProxy.updateRetenue(retenue);
		}
		
		return savedretenue;
	}


}
