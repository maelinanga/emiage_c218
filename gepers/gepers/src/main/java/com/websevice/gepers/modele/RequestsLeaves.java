package com.websevice.gepers.modele;

import java.util.Date;



import lombok.Data;
@Data
public class RequestsLeaves {

	private Integer id;
	private Date depart;
	private Date retour;
	private int annee;
	private int solde;
	private int statut;
	private int conge_id;
	private int personnel_id;
}
