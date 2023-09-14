package com.example.demo.config;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	
	private SecretKey secretKey;

	public WebSecurityConfig() {
		super();
		this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	@Bean
	public SecretKey secretKey() {
		return this.secretKey;
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and()
				.csrf().disable();
				
				http.cors().and().csrf().disable();
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				
				http.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/project/regularusers").permitAll()
				.antMatchers(HttpMethod.POST, "/social-media/login").permitAll()
				.antMatchers(HttpMethod.PUT, "/forgot-password").permitAll()
				.anyRequest().authenticated();
				
				http.addFilterBefore(new JWTAuthorizationFilter(secretKey), UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
	}
	

}
