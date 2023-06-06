package com.websevice.gepers.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Iterator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.websevice.gepers.modele.Employee;
import com.websevice.gepers.modele.Leaves;
import com.websevice.gepers.modele.RequestsLeaves;
import com.websevice.gepers.service.EmployeeService;
import com.websevice.gepers.service.LeavesService;
import com.websevice.gepers.service.PaieService;
import com.websevice.gepers.service.RequestsLeavesService;

import lombok.Data;




@Data
@Controller
@Secured(value={"USER","ADMIN"})
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private RequestsLeavesService requestservice;
	
	@Autowired
	private LeavesService leaves;
	
	@Autowired
	private PaieService payrollservice;
	
	@Autowired
	public LoginController log;
	
	@GetMapping("/mesconges")
	public String myRequestsleaves(HttpServletRequest httpServletRequest, Model model) {
			
		String username = log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		Employee emp=service.getEmployeeByUsername(username);
		Integer emp_id = emp.getId();
		model.addAttribute("id_employee",emp_id);
		
		//demandes en cours de l'mployé
		
		Iterable <RequestsLeaves> mypendingR= requestservice.getMyPendingRequests(emp.getId(), 0);
		model.addAttribute("mypendingR",mypendingR);
		
		
		//demandes validées par le RH
		
		Iterable <RequestsLeaves> myRequestsValidate= requestservice.getMyPendingRequests(emp.getId(), 1);
		model.addAttribute("myRequestsValidate",myRequestsValidate);
		
		/*
		 *  toutes mes demandes
		 
		
		Iterable <RequestsLeaves> myrequests= requestservice.getMyrequests(emp.getId());
		model.addAttribute("myrequests",myrequests);
		int countRequests= myrequests.;
		model.addAttribute("countRequests",countRequests);
		*/
		//solde
		
		
		
		
				
		return "conges_mesdemandes";
	}
	
	
	
	@GetMapping("/monprofil")
	public String monprofil(HttpServletRequest httpServletRequest, Model model) {
		String username = log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		Employee profil=service.getEmployeeByUsername(username);
		
		model.addAttribute("profil",profil);
		return "mon_profil";
	}
	
	
	@GetMapping("/createRequest")
	public String createRequest(HttpServletRequest httpServletRequest,Model model) {
		String username = log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		Employee emp=service.getEmployeeByUsername(username);
		model.addAttribute("id_employee",emp.getId());
		
		RequestsLeaves p = new RequestsLeaves();
		model.addAttribute("request", p);
		Iterable <Leaves> typeconges=leaves.getLeaves();
		model.addAttribute("typeconges",typeconges);
		
		/*
		 * recherche demandes congés annuels encours 
		 */
		LocalDate currentdate = LocalDate.now();
		int annee_encours  = currentdate.getYear();		//c.get(Calendar.YEAR);
		 model.addAttribute("annee_encours",annee_encours);
		Iterable<RequestsLeaves> demandes_encours = requestservice.getMyPendingAnnualRequests(emp.getId(), 1, 0,annee_encours);
		
		Iterator<RequestsLeaves> iterator = demandes_encours.iterator();
		if (iterator.hasNext()) {
			// Iterable has at least one element
			 int conge_annuel=1;
			  model.addAttribute("conge_annuel",conge_annuel);
		 }
		else {
			    	int conge_annuel=0;
			         model.addAttribute("conge_annuel",conge_annuel);
			    }
			    
		/*
		 * // recherche de la dernière demande de congé annuel validé///////////
		 */
		Iterable<RequestsLeaves> demandes_valides= requestservice.getMyPendingAnnualRequests(emp.getId(), 1, 1,annee_encours);
		Iterator<RequestsLeaves> iterator_solde = demandes_valides.iterator();
		if (iterator_solde.hasNext()) {
			    	 
			int maxId = Integer.MIN_VALUE; // Initialisation de l'ID temporaire à la plus petite valeur possible
			for (RequestsLeaves e : demandes_valides) { // Parcourir l'itération d'objets "RequestLeaves"
				if (e.getId() > maxId) { // Vérifier si l'ID de la demande actuelle est plus grand que l'ID temporaire
					maxId = e.getId(); // Si c'est le cas, mettre à jour l'ID temporaire avec la valeur actuelle
				}
			}
			    	
			//récupérer la valeur du solde de la dernière demande de congé annuel
			RequestsLeaves lastrequest=requestservice.getRequest(maxId);
			int solde_annuel = lastrequest.getSolde();
			model.addAttribute("solde_annuel",solde_annuel);
			    		    	
		}
		else {
			int solde_annuel = 26;
			model.addAttribute("solde_annuel",solde_annuel);
		}
		//////////////////////////////////////////////////////////////////
		/*
		 * verification existence demandes congé maternité en cours
		 */
		
		Iterable<RequestsLeaves> conge_maternite_encours= requestservice.getMyPendingAnnualRequests(emp.getId(), 2, 0,annee_encours);
		Iterator<RequestsLeaves> recher = conge_maternite_encours.iterator();
		if (recher.hasNext()) {
			// Iterable has at least one element
			int conge_maternite=1;
			model.addAttribute("conge_maternite",conge_maternite);  
			         		      	         
		}
		else
		//aucune demande maternité encours	 
		{
			int conge_maternite=0;
			model.addAttribute("conge_maternite",conge_maternite); 
		}
				
		
			    
		return "formdemande_conge";
	}
	
	@PutMapping("/updateRequest/{id}")
	public String updateRequest(@PathVariable("id") final int id, Model model) {
		RequestsLeaves p = requestservice.getRequest(id);		
		model.addAttribute("request", p);
		
		
		return "formUpdateRequest";		
	}
	
	@GetMapping("/deleteRequest/{id}")
	public ModelAndView deleteRequest(@PathVariable("id") final int id) {
		requestservice.deleteRequest(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveRequest")
	public ModelAndView saveRequest(@ModelAttribute RequestsLeaves request) {
		requestservice.saveRequest(request);
	    		
		 return new ModelAndView("redirect:/monprofil");		
			
	}
	
	

	
}
