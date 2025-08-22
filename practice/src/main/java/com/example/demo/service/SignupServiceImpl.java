package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.AdminRepository;

//SignupServiceのインターフェース（設計図）の中身（実装）のこと
//インターフェース＝ルールを記述したクラスのこと
//Serviceクラス→ビジネスロジック(処理の中身)を記述するクラスのこと

//public class SignupServiceImplは、SignupServiceを実際にどのように使うかを記述したクラス
//implements SignupServiceは、インターフェースであることを記述している
//インターフェースは、「こんな機能が必要！」とだけ記述されていて、実際の処理の内容は記述されていない。

@Service
//SignupServiceというインターフェースに記述されている機能(メソッドの名前や形)をSignupServiceImplクラスで実際に実装（処理する中身）しますと宣言している。
public class SignupServiceImpl implements SignupService {
	
//データベースとやり取りするクラス
	@Autowired
		private AdminRepository adminRepository;
//RepositoryからAdminの引数を使う	
	@Override
	public void saveSignup(SignupForm signupForm) {
		System.out.println("serviceクラス：signupFormの詳細" + signupForm);
		
		Admin signup = new Admin();
		System.out.println("serviceクラス set前：signupFormの詳細" + signup);
		
//実際に処理したい内容を下記で記述している
		signup.setLastName(signupForm.getLastName());
		signup.setFirstName(signupForm.getFirstName());
		signup.setEmail(signupForm.getEmail());
		signup.setPassword(signupForm.getPassword());
		
		System.out.println("serviceクラス set後：signupFormの詳細" + signup);
		
		adminRepository.save(signup);
	}
}
