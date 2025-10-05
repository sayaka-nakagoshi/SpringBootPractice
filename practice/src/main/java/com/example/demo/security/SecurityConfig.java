package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.AdminUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private AdminUserDetailsService adminUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/webjars/**", "/css/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/contact", "/contact/**").permitAll()
				.requestMatchers("/webjars/**", "/css/**", "/admin/signin/**", "/admin/signup/**").permitAll()
				.requestMatchers("/admin/contacts/**").hasRole("ADMIN")
				.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.accessDeniedPage("/admin/signin"))

				//ログインに関する処理
				.formLogin(form -> form
						.loginProcessingUrl("/admin/signin")
						.loginPage("/admin/signin")
						.failureUrl("/admin/signin?error")
						.usernameParameter("email")
						.passwordParameter("password")
						.defaultSuccessUrl("/admin/contacts", true)
						.permitAll())

				//ログアウトに関する処理
				.logout(logout -> logout
						.logoutUrl("/admin/logout")
						.logoutSuccessUrl("/admin/signin")
						.permitAll())

				.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(adminUserDetailsService).passwordEncoder(passwordEncoder);
	}
}
