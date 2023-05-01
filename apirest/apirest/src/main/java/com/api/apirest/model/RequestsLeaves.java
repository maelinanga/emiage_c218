package com.api.apirest.model;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "demandesconges")	
public class RequestsLeaves {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_depart")
    private Date depart;
    
    @Column(name="date_retour")
    private Date retour;
    
    @Column(name="solde")
    private int solde;
    
    @Column(name="statut")
    private int statut;
    
   /* @OneToMany(
    cascade = CascadeType.ALL, 
    orphanRemoval = true, 
    fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    List<Leaves> conges = new ArrayList<>();*/
    
    //@OneToOne
    @Column(name="conge_id")
    private int conge_id;
    
    //@OneToOne
    @Column(name="employee_id")
    private int employee;
    

	
}