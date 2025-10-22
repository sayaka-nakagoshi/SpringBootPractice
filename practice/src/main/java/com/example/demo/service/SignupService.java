package com.example.demo.service;

import com.example.demo.form.SignupForm;

public interface SignupService {
	void saveSignup(SignupForm signupform);

	//メールアドレスが重複している場合はエラー追加
	boolean isEmailRegisteredError(String email);
}
