package com.costamar.ninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.costamar.ninja.constant.ViewConstant;

@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout
			){
		LOG.info("METHOD: showLoginForm " );
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		LOG.info("returning to login view" );
		return ViewConstant.LOGIN;
	}
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck() {
		LOG.info("METHOD: showLoginForm PARAMS: ");
		LOG.info("redirect to login error" );
		return "redirect:/contacts/showcontacts";
	}
	
	

}
