package com.websevice.gepers.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.websevice.gepers.modele.Employee;

import com.websevice.gepers.modele.RequestsLeaves;
import com.websevice.gepers.service.EmployeeService;
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
	public String createRequest(HttpServletRequest httpServletRequest, Model model) {
		String username = log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		Employee profil=service.getEmployeeByUsername(username);
		
		model.addAttribute("profil",profil);
		return "mon_profil";
	}
	
	
	@GetMapping("/createRequest")
	public String createRequest(Model model) {
		RequestsLeaves p = new RequestsLeaves();
		model.addAttribute("request", p);
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
	public ModelAndView savePrime(@ModelAttribute RequestsLeaves request) {
		
		requestservice.saveRequest(request);
		return new ModelAndView("redirect:/");	
	}
	
	

	
}
