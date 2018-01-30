package com.costamar.ninja.component;

import org.springframework.stereotype.Component;

import com.costamar.ninja.entity.Contact;
import com.costamar.ninja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public Contact convertContalModel2Contact(ContactModel contactModel){
		Contact contact = new Contact();
		contact.setCity(contactModel.getCity());
		contact.setId(contactModel.getId());
		contact.setFirstname(contactModel.getFirstname());
		contact.setLastname(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		return contact;
	}

	public ContactModel convertContal2ContactModel(Contact contact){
		ContactModel contactModel = new ContactModel();
		contactModel.setCity(contact.getCity());
		contactModel.setFirstname(contact.getFirstname());
		contactModel.setId(contact.getId());
		contactModel.setLastname(contact.getLastname());
		contactModel.setTelephone(contact.getTelephone());
		
		return contactModel;
	}
	
}
