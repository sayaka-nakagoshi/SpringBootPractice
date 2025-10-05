package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AdminContactForm {
	    
	    // 編集対象外だが、IDとして必要
	    private Long id; 

	    // 編集可能なカラムをすべて定義する
	    @NotBlank
	    private String lastName;

	    @NotBlank
	    private String firstName;
	    
	    // ... 他の編集対象フィールドをすべて定義 ...
	    private String email;
	    private String phone;
	    private String zipCode;
	    private String address;
	    private String buildingName;
	    private String contactType;
	    private String body;
	    
	    // ※createdAt, updatedAtは編集しないため、通常フォームには含めない
}
