package com.costamar.ninja.repository;

import org.springframework.stereotype.Repository;
import com.costamar.ninja.entity.Contact;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Serializable> {
	
	public abstract Contact findById(int id);
				
	

}
