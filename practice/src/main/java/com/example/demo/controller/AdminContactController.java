package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.AdminContactForm;
import com.example.demo.service.AdminContactService;

@Controller
public class AdminContactController {

	@Autowired
	private AdminContactService adminContactService;

	@GetMapping("/admin/contacts")
	public String showContacts(Model model) {
		List<Contact> contacts = adminContactService.findAllContacts();

		model.addAttribute("contacts", contacts);

		return "AdminContacts";
	}

	//詳細画面を開く
	@GetMapping("/admin/contacts/{id}")
	public String showContactsDetail(@PathVariable("id") Long id, Model model) {

		Optional<Contact> contactOptional = adminContactService.findContactById(id);

		if (contactOptional.isPresent()) {
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

		adminContactService.deleteContact(id);

		return "redirect:/admin/contacts";
	}

	//編集画面を表示
	@GetMapping("/admin/contacts/{id}/edit")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Optional<Contact> contactOptional = adminContactService.findContactById(id);

		System.out.println("Service");

		if (contactOptional.isPresent()) {
			Contact contact = contactOptional.get();

			AdminContactForm adminContactForm = adminContactService.createForm(contact);

			model.addAttribute("adminContactForm", adminContactForm);
			return "AdminContactEdit";
		}

		else {
			return "redirect:/admin/contacts";
		}
	}

	//編集画面で内容変更
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable("id") Long id,
			@Validated @ModelAttribute("adminContactForm") AdminContactForm adminContactForm,
			BindingResult errorResult, Model model) {

		if (errorResult.hasErrors()) {
			return "AdminContactEdit";
		}

		if (!id.equals(adminContactForm.getId())) {
			return "redirect:/admin/contacts";
		}

		Contact updatedContact = adminContactService.updateContact(id, adminContactForm);

		if (updatedContact != null) {
			return "redirect:/admin/contacts/" + id;
		}

		else {
			return "redirect:/admin/contacts";
		}
	}
}