package com.api.apirest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.api.apirest.model.Retenues;
import com.api.apirest.model.Primes;
import com.api.apirest.model.Employee;

import lombok.Data;
@Data
@Entity
@Table(name = "paie")

public class Paie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="mois")
	private int mois;
	
	@Column(name="annee")
	private int annee;
	
	@Column(name="montant_retenue")
	private float montantretenue;
	@Column(name="montant_prime")
	private float montantprime;
	
	@Column(name="salaire_net")
	private float salaireNet;
	
	@OneToOne
    private Retenues retenue;
	
	@OneToOne
    private Primes prime;
	
	@OneToOne
    private Employee employee;
	


}
