package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

//implements Serializableは、このクラスは保存や送信できるようにするという意味
@Data
public class SignupForm implements Serializable{
//NotBlankを入れることによって空文字もしくは空白の時にエラー表示させるようにできることができるアノテーション
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
		@Email
	private String email;
	
	@NotBlank
		
	private String password;
	
}
