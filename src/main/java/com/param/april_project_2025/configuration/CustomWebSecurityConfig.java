package com.param.april_project_2025.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class CustomWebSecurityConfig {

	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final PasswordEncoder passwordEncoder;

	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .authorizeRequests()
        .requestMatchers("/public/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic(config -> config.authenticationEntryPoint(restAuthenticationEntryPoint));

		return http.build();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.inMemoryAuthentication().withUser("testParam")
				.password(passwordEncoder.encode("Param@123")).roles("ADMIN").and().withUser("testUser")
				.password(passwordEncoder.encode("User@123")).roles("USER");

		return authenticationManagerBuilder.build();
	}
}