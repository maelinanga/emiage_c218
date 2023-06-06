package com.websevice.gepers.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websevice.gepers.modele.RequestsLeaves;
import com.websevice.gepers.repository.RequestsLeavesProxy;

import lombok.Data;

@Data
@Service

public class RequestsLeavesService {
	
	@Autowired
	private RequestsLeavesProxy demandecongeProxy;
	
	public RequestsLeaves getRequest(final int id) {
		return demandecongeProxy.getRequest(id);
	}
	
	public Iterable<RequestsLeaves> getRequests() {
		return demandecongeProxy.getRequests();
	}
	
	public Iterable <RequestsLeaves> getMyrequests(final int employe){
		return demandecongeProxy.getRequestByEmployee(employe);
	}
	
	public Iterable <RequestsLeaves> getMyPendingRequests(final int emp, int statut){
		return demandecongeProxy.getMyRequestPendding(emp, statut);
	}
	
	public Iterable <RequestsLeaves> getMyPendingAnnualRequests(final int emp,int type_conge, int statut, int annee){
		return demandecongeProxy.getMyAnnualRequestPendding(emp, type_conge, statut, annee);
	}
	
	public Iterable <RequestsLeaves> getPendingRequests(final int statut){
		return demandecongeProxy.getRequestsPenddings(statut);
	}
	public void deleteRequest(final int id) {
		demandecongeProxy.deleteRequest(id);
	}
	
	public RequestsLeaves saveRequest(RequestsLeaves request) {
		
		
		RequestsLeaves savedRequest;
		

		if(request.getId() == null) {
			// If id is null, then it is a new employee.
			savedRequest = demandecongeProxy.createRequest(request);
		} else {
			savedRequest = demandecongeProxy.updateRequest(request);
		}
		
		return savedRequest;
	}

	
}
