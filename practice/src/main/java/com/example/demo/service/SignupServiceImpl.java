package com.example.demo.service;

import java.util.Optional;

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

	//登録する時に、パスワードを暗号化させている
	@Override
	public void saveSignup(SignupForm signupForm) {

		Admin signup = new Admin();

		signup.setLastName(signupForm.getLastName());
		signup.setFirstName(signupForm.getFirstName());
		signup.setEmail(signupForm.getEmail());
		signup.setPassword(passwordEncoder.encode(signupForm.getPassword()));

		adminRepository.save(signup);
	}

	//登録する時に、メールアドレスが重複しているか確認
	@Override
	public boolean isEmailRegisteredError(String email) {
		Optional<Admin> existingEmail = adminRepository.findByEmail(email);

		return existingEmail.isPresent();
	}

}
