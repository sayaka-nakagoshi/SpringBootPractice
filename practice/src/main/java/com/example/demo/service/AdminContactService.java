package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;

public interface AdminContactService {
	List<Contact> findAllContacts();
	
	Optional<Contact> findContactById(Long id);
	
	void deleteContact(Long id);
	
	Contact saveContact(Contact contact);
	
	Contact updateContact(Long id,Contact formContact);
}
