package com.costamar.ninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.costamar.ninja.constant.ViewConstant;
import com.costamar.ninja.model.ContactModel;
import com.costamar.ninja.service.ContactService;

@Controller
//@PreAuthorize("permitAll()") SE PUEDE AGREGAR A NIVEL DE CLASE, permitall permite a todos.
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;
	
	@GetMapping("/cancel")
	private String cancel(){
		return "redirect:/contacts/showcontacts";
	}
	
	//permite que el usuario logeado no pueda verlo dependiendo de su rol
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/contactform")
	private String goToContactForm(@RequestParam(name="id",required=true) int id,
			Model model){
		ContactModel contactModel = new ContactModel();
		
		if(id != 0){
			contactModel = contactService.findContactByIdModel(id);
		}
		
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}
	
	//@ModelAttribute se relaciona con th:object
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			Model model) {
		LOG.info("METHOD: addcontact PARAMS: " + contactModel.toString() );
		if(contactService.addContact(contactModel) != null){
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		
		return "redirect:/contacts/showcontacts";
	}
	
	@GetMapping("/showcontacts")
	public ModelAndView showContacts(){
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.listAll());
		return mav;
	}
	
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name="id",required=true) int id){
		contactService.removeContact(id);
		return showContacts();
	}
	
	
	
}
