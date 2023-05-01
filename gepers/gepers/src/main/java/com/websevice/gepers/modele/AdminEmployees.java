package com.websevice.gepers.modele;

import java.util.Date;

import com.websevice.gepers.modele.Employee;
import com.websevice.gepers.modele.Fonction;
import com.websevice.gepers.modele.Grade;

import lombok.Data;
@Data

public class AdminEmployees {
	private Integer id;
	private Date dateFonction;
	private float honoraires;
	private String nif;
	private String numeroCompte;
	private String banque;
	private Grade grade;
	private Fonction fonction;
	private Employee employee;
	private float tauxImpot;

}
