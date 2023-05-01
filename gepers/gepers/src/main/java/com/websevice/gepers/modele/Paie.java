package com.websevice.gepers.modele;
import com.websevice.gepers.modele.Primes;

import lombok.Data;
@Data

public class Paie {
	private Integer id;
	private int mois;
	private int annee;
	private float montantretenue;
	private float montantprime;
	private float salaireNet;
	private Retenues retenue;
	private Primes prime;
	private Employee employee;

}
