package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;
import com.example.demo.form.AdminContactForm;

public interface AdminContactService {
	List<Contact> findAllContacts();

	Optional<Contact> findContactById(Long id);

	void deleteContact(Long id);

	Contact updateContact(Long id, AdminContactForm adminContactForm);

	AdminContactForm createForm(Contact contact);
}