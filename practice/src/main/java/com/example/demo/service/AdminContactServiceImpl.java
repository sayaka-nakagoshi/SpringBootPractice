package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.AdminContactForm;
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
	public AdminContactForm createForm(Contact contact) {
		if (contact == null) {
			return null;
		}

		AdminContactForm form = new AdminContactForm();
		form.setId(contact.getId());
		form.setLastName(contact.getLastName());
		form.setFirstName(contact.getFirstName());
		form.setEmail(contact.getEmail());
		form.setPhone(contact.getPhone());
		form.setZipCode(contact.getZipCode());
		form.setAddress(contact.getAddress());
		form.setBuildingName(contact.getBuildingName());
		form.setContactType(contact.getContactType());
		form.setBody(contact.getBody());

		return form;
	}

	@Override
	public Contact updateContact(Long id, AdminContactForm adminContactForm) {
		Optional<Contact> existingContactOpt = contactRepository.findById(id);

		if (existingContactOpt.isPresent()) {
			Contact existingContact = existingContactOpt.get();

			existingContact.setLastName(adminContactForm.getLastName());
			existingContact.setFirstName(adminContactForm.getFirstName());
			existingContact.setEmail(adminContactForm.getEmail());
			existingContact.setPhone(adminContactForm.getPhone());
			existingContact.setZipCode(adminContactForm.getZipCode());
			existingContact.setAddress(adminContactForm.getAddress());
			existingContact.setBuildingName(adminContactForm.getBuildingName());
			existingContact.setContactType(adminContactForm.getContactType());
			existingContact.setBody(adminContactForm.getBody());

			return contactRepository.save(existingContact);
		}
		return null;
	}
}
