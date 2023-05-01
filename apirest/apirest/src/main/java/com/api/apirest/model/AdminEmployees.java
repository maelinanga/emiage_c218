package com.api.apirest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.api.apirest.model.Grade;
import com.api.apirest.model.Fonction;


import lombok.Data;
@Data
@Entity
@Table(name = "admin_info_employees")

public class AdminEmployees {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date_fonction")
    private Date dateFonction;
    
    @Column(name="honoraires")
    private float honoraires;
    
    @Column(name="nif")
    private String nif;
    
    @Column(name="numero_compte")
    private String numeroCompte;
    
    @Column(name="Banque")
    private String banque;
    
    @OneToOne
    private Grade grade;
    
    @OneToOne
    private Fonction fonction;
    
    @OneToOne
    private Employee employee;
    
    @Column(name="taux_impot")
    private float tauxImpot;

}
