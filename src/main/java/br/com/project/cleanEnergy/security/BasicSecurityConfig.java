package br.com.project.cleanEnergy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	private @Autowired UserDetailsServiceImplements service;

	@Bean
	public PasswordEncoder senhaEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override

	protected void configure (HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/v1/usuario/salvar").permitAll()
		.antMatchers(HttpMethod.POST, "/api/v1/usuario/logar").permitAll()
		.antMatchers(HttpMethod.GET, "/api/v1/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/produtos").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.and().cors()
		.and().csrf().disable();

	protected void configure(HttpSecurity http) throws Exception {
<<<<<<< HEAD
		http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/usuario/salvar").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/usuario/logar").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/usuario").permitAll()
				.antMatchers(HttpMethod.GET, "/produtos").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable();
=======
	http.authorizeRequests()
	.antMatchers("/usuarios/cadastrar").permitAll()
	.antMatchers("/usuarios/logar").permitAll()
	.antMatchers(HttpMethod.OPTIONS).permitAll()
	.anyRequest().authenticated()
	.and().httpBasic()
	.and().sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and().cors()
	.and().csrf().disable();

>>>>>>> c456cdaf6eccf3f79b89c48904823f291bec7629
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
		auth.inMemoryAuthentication().withUser("root").password(senhaEncoder().encode("root")).authorities("ROLE_ADMIN");
	}

}