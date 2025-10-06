package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.AdminRepository;

@Service
public class SignupServiceImpl implements SignupService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveSignup(SignupForm signupForm) {
		System.out.println("serviceクラス：signupFormの詳細" + signupForm);

		Admin signup = new Admin();
		System.out.println("serviceクラス set前：signupFormの詳細" + signup);

		//登録する時に、パスワードを暗号化させている
		signup.setLastName(signupForm.getLastName());
		signup.setFirstName(signupForm.getFirstName());
		signup.setEmail(signupForm.getEmail());
		signup.setPassword(passwordEncoder.encode(signupForm.getPassword()));

		System.out.println("serviceクラス set後：signupFormの詳細" + signup);

		adminRepository.save(signup);

	}
}
