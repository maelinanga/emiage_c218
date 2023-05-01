package com.websevice.gepers.modele;

import java.util.Date;

import lombok.Data;
@Data

public class Visiteurs {
	private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private float  revenusfoyer;
    private float  montantcredit;
    private float  montantdemande;
    private Integer  dureecredit;
    private float  montantmensualites;
    private Boolean contact;
    private Date datevisit;
    private int typecredit;
	

}
