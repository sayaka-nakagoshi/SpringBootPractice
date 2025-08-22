package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
//実際には登録した内容を送信する時に使用している。Entityはテーブルへの橋渡し。データベースとjavaの記述内容を行き来している。
//全カラムの内容を1つにする。
//database→entity→repository→service→controller→thymeleaf→html
//Entityにはデータベースで作成したテーブルは1つまでしか記述できない
@Entity
@Data
@Table(name = "admins_table")
public class Admin{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
}