package com.api.apirest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller

public class MyErrorController implements ErrorController  {

	/*@RequestMapping("/error")
	  @ResponseBody
	  String error(HttpServletRequest request) {
	    return "<h1>Error occurred</h1>";
	  }
	
	@Override
	@Deprecated
      
	  public String getErrorPath() {
	    return "/error";
	  }*/
	@RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }

}
