package com.websevice.gepers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.websevice.gepers.modele.Employee;
import com.websevice.gepers.modele.Fonction;
import com.websevice.gepers.modele.Grade;
import com.websevice.gepers.modele.Leaves;
import com.websevice.gepers.modele.Paie;
import com.websevice.gepers.modele.Primes;
import com.websevice.gepers.modele.RequestsLeaves;
import com.websevice.gepers.modele.Retenues;
import com.websevice.gepers.service.EmployeeService;
import com.websevice.gepers.service.FonctionService;
import com.websevice.gepers.service.GradeService;
import com.websevice.gepers.service.LeavesService;
import com.websevice.gepers.service.PaieService;
import com.websevice.gepers.service.PrimeService;
import com.websevice.gepers.service.RequestsLeavesService;
import com.websevice.gepers.service.RetenueService;

import lombok.Data;

@Data
@Controller
@Secured(value={"ADMIN"})
public class ManagerController {
	@Autowired
	private EmployeeService service;

	@Autowired
	private FonctionService fonctionservice;
	
	@Autowired
	private LeavesService leaveservice;
	
	@Autowired
	private PaieService payrollservice;
	
	@Autowired
	private PrimeService primeservice;
	
	@Autowired
	private RequestsLeavesService requestservice;
	
	@Autowired
	private RetenueService retenueservice;
	
	@GetMapping("/listemployees")
	public String home(Model model) {
		Iterable<Employee> listEmployee = service.getEmployees();
		model.addAttribute("employees", listEmployee);
		return "home";
	}
	
	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "formNewEmployee";
	}
	
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable("id") final int id, Model model) {
		Employee e = service.getEmployee(id);		
		model.addAttribute("employee", e);	
		return "formUpdateEmployee";		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
		service.deleteEmployee(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if(employee.getId() != null) {
			// Employee from update form has the password field not filled,
			// so we fill it with the current password.
			Employee current = service.getEmployee(employee.getId());
			employee.setPassword(current.getPassword());
		}
		service.saveEmployee(employee);
		return new ModelAndView("redirect:/");	
	}
	
	//fonction
	@GetMapping("/listfonctions")
	public String fonction(Model model) {
		Iterable<Fonction> listFonction = fonctionservice.getFonctions();
		model.addAttribute("fonctions", listFonction);
		return "";
	}
	
	@GetMapping("/createFonction")
	public String createFonction(Model model) {
		Fonction f = new Fonction();
		model.addAttribute("fonction", f);
		return "formNewFonction";
	}
	
	@GetMapping("/updateFonction/{id}")
	public String updateFonction(@PathVariable("id") final int id, Model model) {
		Fonction f = fonctionservice.getFonction(id);		
		model.addAttribute("fonction", f);	
		return "formUpdateFonction";		
	}
	
	@GetMapping("/deleteFonction/{id}")
	public ModelAndView deleteFonction(@PathVariable("id") final int id) {
		fonctionservice.deleteFonction(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveFonction")
	public ModelAndView saveFonction(@ModelAttribute Fonction fonction) {
		
		fonctionservice.saveFonction(fonction);
		return new ModelAndView("redirect:/");	
	}

	
	//Grade
	
	@Autowired
	private GradeService gradeservice;
	
	@GetMapping("/listgrades")
	public String grade(Model grademodel) {
		Iterable<Grade> listgrades = gradeservice.getGrades();
		grademodel.addAttribute("grades", listgrades);
		return "formListGrades";
	}
	
	@GetMapping("/createGrade")
	public String createGrade(Model model) {
		Grade g = new Grade();
		model.addAttribute("grade", g);
		return "formNewGrade";
	}
	
	@GetMapping("/updateGrade/{id}")
	public String updateGrade(@PathVariable("id") final int id, Model model) {
		Grade g = gradeservice.getGrade(id);		
		model.addAttribute("grade", g);	
		return "formUpdateGrade";		
	}
	
	@GetMapping("/deleteGrade/{id}")
	public ModelAndView deleteGrade(@PathVariable("id") final int id) {
		gradeservice.deleteGrade(id);
		return new ModelAndView("redirect:/formListGrades");		
	}
	
	@PostMapping("/saveGrade")
	public ModelAndView saveGrade(@ModelAttribute Grade grade) {
		
		gradeservice.saveGrade(grade);
		return new ModelAndView("redirect:/formListGrades");	
	}
	
	
	//Congés
	
	@GetMapping("/listleaves")
	public String leaves(Model model) {
		Iterable<Leaves> listleave = leaveservice.getLeaves();
		model.addAttribute("leaves", listleave);
		return "formListconges";
	}
	
	@GetMapping("/createLeave")
	public String createLeave(Model model) {
		Leaves l = new Leaves();
		model.addAttribute("leave", l);
		return "formNewLeave";
	}
	
	@GetMapping("/updateLeave/{id}")
	public String updateLeave(@PathVariable("id") final int id, Model model) {
		Leaves l = leaveservice.getLeave(id);		
		model.addAttribute("leave", l);	
		return "formUpdateconge";		
	}
	
	@GetMapping("/deleteLeave/{id}")
	public ModelAndView deleteLeave(@PathVariable("id") final int id) {
		leaveservice.deleteLeave(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/saveLeave")
	public ModelAndView saveLeave(@ModelAttribute Leaves leave) {
		
		leaveservice.saveLeave(leave);
		return new ModelAndView("redirect:/");	
	}
	
// Paie
	
	@GetMapping("/listPayroll")
	public String payrolls(Model model) {
		Iterable<Paie> listpayroll = payrollservice.getPaies();
		model.addAttribute("salary", listpayroll);
		return "formListSalary";
	}
	
	@GetMapping("/createPayroll")
	public String createPaie(Model model) {
		Paie g = new Paie();
		model.addAttribute("payroll", g);
		return "formNewPayroll";
	}
	
	@GetMapping("/updatePayroll/{id}")
	public String updatePaie(@PathVariable("id") final int id, Model model) {
		Paie p = payrollservice.getPaie(id);		
		model.addAttribute("payroll", p);	
		return "formUpdatePayroll";		
	}
	
	@GetMapping("/deletePayroll/{id}")
	public ModelAndView deletePaie(@PathVariable("id") final int id) {
		payrollservice.deletePaie(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/savePayroll")
	public ModelAndView savePaie(@ModelAttribute Paie payroll) {
		
		payrollservice.savePaie(payroll);
		return new ModelAndView("redirect:/");	
	}	
	
	
	
	//Primes
	
	@GetMapping("/listprimes")
	public String primes(Model model) {
		Iterable<Primes> listprime = primeservice.getPrimes();
		model.addAttribute("primes", listprime);
		return "formListPrimes";
	}
	
	@GetMapping("/createPrime")
	public String createPrime(Model model) {
		Primes p = new Primes();
		model.addAttribute("prime", p);
		return "formNewPrime";
	}
	
	@GetMapping("/updatePrime/{id}")
	public String updatePrime(@PathVariable("id") final int id, Model model) {
		Primes p = primeservice.getPrime(id);		
		model.addAttribute("prime", p);	
		return "formUpdatePrime";		
	}
	
	@GetMapping("/deletePrime/{id}")
	public ModelAndView deletePrime(@PathVariable("id") final int id) {
		primeservice.deletePrime(id);
		return new ModelAndView("redirect:/");		
	}
	
	@PostMapping("/savePrime")
	public ModelAndView savePrime(@ModelAttribute Primes prime) {
		
		primeservice.savePrime(prime);
		return new ModelAndView("redirect:/");	
	}

//demandes congés
	
	
	//retenues
	
	@GetMapping("/listretenues")
	public String retenues(Model model) {
		Iterable<Retenues> listretenues = retenueservice.getRetenues();
		model.addAttribute("retenues", listretenues);
		return "formListRetenues";
	}
	
	@GetMapping("/createRetenue")
	public String createRetenue(Model model) {
		Retenues g = new Retenues();
		model.addAttribute("retenue", g);
		return "formNewRetenue";
	}
	
	@GetMapping("/updateRetenue/{id}")
	public String updateRetenue(@PathVariable("id") final int id, Model model) {
		Retenues g = retenueservice.getRetenue(id);		
		model.addAttribute("retenue", g);	
		return "formUpdateRetenue";		
	}
	
	@GetMapping("/deleteRetenue/{id}")
	public ModelAndView deleteRetenue(@PathVariable("id") final int id) {
		retenueservice.deleteRtenue(id);
		return new ModelAndView("redirect:/formListRetenues");		
	}
	
	@PostMapping("/saveRetenue")
	public ModelAndView saveRetenue(@ModelAttribute Retenues retenue) {
		
		retenueservice.saveRetenue(retenue);
		return new ModelAndView("redirect:/formListRetenues");	
	}

	
}
