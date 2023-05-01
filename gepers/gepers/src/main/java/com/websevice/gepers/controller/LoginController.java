package com.websevice.gepers.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController

public class LoginController {

	public String getLogedUser(HttpServletRequest httpServletRequest ){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String login = securityContext.getAuthentication().getName();
		Map<String, Object> params=new HashMap<>();
		params.put("login",login);
		return login;
				
	}
	
	   @RolesAllowed("USER")
	   @RequestMapping(value="/user", method = RequestMethod.GET)
	   public ModelAndView userPage() {
			ModelAndView model = new ModelAndView();
			model.setViewName("index_employee");
			return model;
		}


	   @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
		public ModelAndView adminPage() {
			ModelAndView model = new ModelAndView();
			model.setViewName("index");
			return model;
		}
	   
	   public String getLoggedUser(@AuthenticationPrincipal Authentication authentication) {
	        return authentication.getName();
	    }

}
