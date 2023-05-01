package com.api.apirest.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
@Data
@Entity
@Table(name = "employees")
public class Employee {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name="prenom")
	    private String firstName;

	    public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@Column(name="nom")
	    private String lastName;
		
		@Column(name="mail")
	    private String mail;
		
		@Column(name="sexe")
		private String sexe;

	    public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		@Column(name="adresse")
		private String adresse;
		
		@Column(name="telephone")
		private String telephone;
		
		@Column(name="etat_civil")
		private String etatcivil;
		
		@Column(name="nbre_enfant")
		private int enfants;
		
		
		@Column(name="date_engagement")
		private Date dateEngagement;
		
		@Column(name="date_naiss")
		private Date dateNaissance;

		@Column(name = "password", length = 64)
		private String password;
		
		@Column(name = "login")
		private String login;
		
		@Column(name="role")
		private int role;
		

}
