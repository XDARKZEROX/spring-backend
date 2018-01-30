package com.costamar.ninja.service;

import java.util.List;

import com.costamar.ninja.entity.Contact;
import com.costamar.ninja.model.ContactModel;

public interface ContactService {

	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAll();
	
	public abstract Contact findContactById(int id);
	
	public abstract void removeContact(int id);

	public abstract ContactModel findContactByIdModel(int id);
}
