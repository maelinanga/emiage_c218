package com.websevice.gepers.modele;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  
	    private Integer id;
	    private String mail;
	   
	    private String firstName;
	    private String lastName;
	    private String password;
	    private String sexe;
	    private String adresse;
	    private String telephone;
	    private String etatcivil;
	    private int enfants;
	    private Date dateEngagement;
	    private Date dateNaissance;
	    private int role;
	    private String login;
	    

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

			    public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		


}
