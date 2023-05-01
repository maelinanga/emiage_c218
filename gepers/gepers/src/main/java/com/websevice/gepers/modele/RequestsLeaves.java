package com.websevice.gepers.modele;

import java.util.Date;



import lombok.Data;
@Data
public class RequestsLeaves {

	private Integer id;
	private Date depart;
	private Date retour;
	private int solde;
	private int statut;
	private Long conge_id;
	private Long personnel_id;
}
