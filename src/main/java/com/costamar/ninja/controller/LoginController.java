package com.costamar.ninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.costamar.ninja.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@GetMapping("/login")
	public String login(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout
			){
		LOG.info("METHOD: showLoginForm " );
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		LOG.info("returning to login view" );
		return ViewConstant.LOGIN;
		
		
	}
	
	public String logincheck()
	{
		System.out.println("dentro de validacion de login");
		return "";
	}
	
	@GetMapping({"/loginsuccess","/"})
	public String loginsuccess()
	{
		LOG.info("METHOD loginCheck()");		
		LOG.info("Returning to contacts view");

	    return "redirect:/contacts/showcontacts";
		
	}
	
}
