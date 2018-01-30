package com.costamar.ninja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.costamar.ninja.component.ContactConverter;
import com.costamar.ninja.entity.Contact;
import com.costamar.ninja.model.ContactModel;
import com.costamar.ninja.repository.ContactRepository;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContalModel2Contact(contactModel));
		return contactConverter.convertContal2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAll() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();
		
		for(Contact contact : contacts) {
			contactsModel.add(contactConverter.convertContal2ContactModel(contact));
		}

		return contactsModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContal2ContactModel(contactRepository.findById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(contact != null){
			contactRepository.delete(id);
		}
	}
	
}
