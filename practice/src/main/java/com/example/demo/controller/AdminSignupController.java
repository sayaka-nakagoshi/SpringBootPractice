package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SigninForm;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;

@Controller
public class AdminSignupController {
	@Autowired
	private SignupService signupService;

	//新規登録画面表示(show signup page)
	@GetMapping("/admin/signup")
	public String showSignup(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("signupForm") != null) {
			model.addAttribute("signupForm", session.getAttribute("signupForm"));
		} else {
			model.addAttribute("signupForm", new SignupForm());
		}
		return "signup";
	}

	//admin/signup　のフォームを送信
	@PostMapping("/admin/signup")
	public String processSignup(@Validated @ModelAttribute("signupForm") SignupForm signupForm,
			BindingResult errorResult, HttpServletRequest request) {
		if (errorResult.hasErrors()) {
			System.out.println("失敗");
			return "signup";
		}
		HttpSession session = request.getSession();
		session.setAttribute("signupForm", signupForm);

		return "redirect:/admin/signup/register";
	}

	//新規登録確認画面表示(show signup confirmation page)
	@GetMapping("/admin/signup/register")
	public String AdminConfirmation(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		model.addAttribute("signupForm", signupForm);
		return "AdminConfirmation";
	}

	//post フォームから渡されたデータを受け取る　redirectで受け取ったデータを指定先に送信
	@PostMapping("/admin/signup/register")
	public String register(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");

		signupService.saveSignup(signupForm);

		return "redirect:/admin/signup/complete";
	}

	//登録画面完了画面
	@GetMapping("/admin/signup/complete")
	public String complete(Model model, HttpServletRequest request) {

		if (request.getSession(false) == null) {
			return "redirect:/admin/signup";
		}

		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		model.addAttribute("signupForm", signupForm);

		session.invalidate();

		return "AdminCompletion";
	}

	//ログイン画面
	@GetMapping("/admin/signin")
	public String showSignin(Model model) {
		model.addAttribute("signinForm", new SigninForm());
		return "signin";
	}

	@PostMapping("/admin/signin")
	public String handleSignin(@Validated @ModelAttribute("signinForm") SigninForm signinForm,
			BindingResult errorResult, HttpServletRequest request) {
		if (errorResult.hasErrors()) {
			System.out.println("singin失敗");
			return "redirect:/admin/signin";
		}

		HttpSession session = request.getSession();
		session.setAttribute("signupForm", signinForm);

		System.out.println("signupFormの詳細" + signinForm);
		
		return "redirect:/admin/contacts";
	}
}
