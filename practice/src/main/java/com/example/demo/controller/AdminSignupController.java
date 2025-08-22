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

import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;

@Controller
public class AdminSignupController {
	@Autowired
	private SignupService signupService;
	
//新規登録画面表示(show signup page)
	@GetMapping("/admin/signup")
	public String showSignup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		
		return "signup";
	}
///admin/signup　のフォームを送信
///BindingResult errorResultはバリデーションの結果を返す
///HttpServletRequest requestはセッションにアクセスするために使用する
	@PostMapping("/admin/signup")
	public String processSignup(@Validated @ModelAttribute("signupForm") SignupForm signupForm, BindingResult errorResult, HttpServletRequest request) {
		if(errorResult.hasErrors()) {
			System.out.println("失敗");
			return "signup";
	}
//エラーがなかったらセッションに保存、確認画面にリダイレクトする		
		HttpSession session = request.getSession();
		session.setAttribute("signupForm", signupForm);
		
//		signupService.saveSignup(signupForm);
		
			System.out.println("signupFormの詳細" + signupForm);
			return "redirect:/admin/signup/register";
	}
	
//新規登録確認画面表示(show signup confirmation page)
//get クライアントからこのデータをくださいって言われたものを返すだけ
	@GetMapping("/admin/signup/register")
	public String AdminConfirmation(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		
		
		signupService.saveSignup(signupForm);
		
		return "AdminConfirmation";
	}

////post フォームから渡されたデータを受け取る　redirectで受け取ったデータを指定先に送る
//	@PostMapping("/admin/signup/register")
//	public String AdminConfirmationPost() {
//	
//		return "redirect:/admin/signup/register";
//	}
	
	@GetMapping("/admin/signup/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		model.addAttribute("signupForm", signupForm);
		return "confirmation";
	}
	
	@PostMapping("/admin/signup/register")
	public String register(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
		
		signupService.saveSignup(signupForm);
		
		return "redirect:/admin/signup/complete";
	}
}

//@Controller
//public class SignupController{
//	@PostMapping("/signup")
//	public ModelAndView contact(@ModelAttribute SignupData signupdata, ModelAndView mv) {
//		
//		mv.addObject("lastName", signupData.getLastName());
//		mv.addObject("firstName", signupData.getFirstName());
//		mv.addObject("email", signupData.getEmail());
//		mv.addObject("password", signupData.getPassword());
//		
//		return mv;
//	}
//}

//@PostMapping("/signup")
////@RequestParam("lastName") String lastName
////"htmlのフォーム作成した時に記述したinputなど"入力された値がString型のlastNameという変数に値が入る
//	public ModelAndView contact(@RequestParam("lastName") String lastName,
//								@RequestParam("firstName") String firstName,
//								@RequestParam("email") String email,
//								@RequestParam("password") String password,
//								ModelAndView mv) {
////ModelAndView（型）mv（変数名、引数）
////confirmation.htmlの画面を表示させる
//		mv.setViewName("confirmation01");
////ビューにどんなデータを渡すか、addObjectで指定する
////mv.addObject("htmテンプレートで使用する名前",javaで受け取った数値);
//		mv.addObject("lastName",lastName);
//		mv.addObject("firstName",firstName);
//		mv.addObject("email",email);
//		mv.addObject("password",password);
//		
//		return mv;
//	}