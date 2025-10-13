package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class AdminContactServiceImpl implements AdminContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}
	
	@Override
	public Optional<Contact> findContactById(Long id) {
		return contactRepository.findById(id);
	}
	
	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
	
	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}
	
	@Override
	public Contact updateContact(Long id,Contact formContact) {
		Optional<Contact> existingContactOpt = contactRepository.findById(id);
		
		if(existingContactOpt.isPresent()) {
			Contact existingContact = existingContactOpt.get();
			
			existingContact.setLastName(formContact.getLastName());
			existingContact.setFirstName(formContact.getFirstName());
			existingContact.setEmail(formContact.getEmail());
			existingContact.setPhone(formContact.getPhone());
			existingContact.setZipCode(formContact.getZipCode());
			existingContact.setAddress(formContact.getAddress());
			existingContact.setBuildingName(formContact.getBuildingName());
			existingContact.setContactType(formContact.getContactType());
			existingContact.setBody(formContact.getBody());
			
			return contactRepository.save(existingContact);
		}
		return null;
	}
}
