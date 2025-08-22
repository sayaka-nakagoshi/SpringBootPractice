package com.example.demo.service;

import com.example.demo.form.SignupForm;

public interface SignupService {
//void メソッド名(引数の型[クラス:SignupFormのオブジェクトを渡す]　引数の変数名)
//「ContactForm クラスで作られたオブジェクトを、contactForm という名前でこのメソッドに渡す」という意味
	void saveSignup(SignupForm signupform);

}
