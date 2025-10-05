package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Controller
public class AdminContactsController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping("/admin/contacts")
	public String showContacts(Model model) {
		List<Contact> contacts = contactRepository.findAll();

		model.addAttribute("contacts", contacts);

		return "AdminContacts";
	}
//詳細画面を開く
	@GetMapping("/admin/contacts/{id}")
	public String showContactsDetail(@PathVariable("id") Long id, Model model) {
		
		Optional<Contact> contactOptional = contactRepository.findById(id);
		
		if(contactOptional.isPresent()) {
			model.addAttribute("contact", contactOptional.get());
			return "AdminContactDetail";
		}
		
		else {
			return "redirect:/admin/contacts";
		}
	}
//お問い合わせ削除処理
	@PostMapping("/admin/contacts/delete/{id}")
	public String deleteContact(@PathVariable("id") Long id) {
		
		contactRepository.deleteById(id);
		
		return "redirect:/admin/contacts";
	}
//編集画面を表示
	@GetMapping("/admin/contacts/{id}/edit")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		
		Optional<Contact> contactOptional = contactRepository.findById(id);
		
		if (contactOptional.isPresent()) {
			Contact contact = contactOptional.get();
			
			model.addAttribute("contact", contact);
			
			return "AdminContactEdit";
			}

			else {
				return "redirect:/admin/contacts";
			}
	}

//編集画面で内容変更
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable("id") Long id, Contact formContact) {
	
		Optional<Contact> existingContactOpt = contactRepository.findById(id);
		
		if (existingContactOpt.isPresent()) {
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
			
			contactRepository.save(existingContact);
			
			return "redirect:/admin/contacts/" + id;
			
			}
			
			else {
				return "redirect:/admin/contacts";
			}
		}

	}
