package com.securityDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.securityDemo.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
 
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin=User.withUsername("adminAvijit").password(encoder.encode("Avijit123")).roles("ADMIN").build();
//		UserDetails user=User.withUsername("user").password(encoder.encode("user123")).roles("USER").build();
//		InMemoryUserDetailsManager(admin,user);
		return new UserInfoUserDetailsService();
	}
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception {
		 return http.csrf().disable().authorizeHttpRequests().requestMatchers("/product/home","/product/add").permitAll()
				 .and()
		.authorizeHttpRequests().requestMatchers("/product/**").authenticated().and().formLogin().and().build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncode());
		return authenticationProvider;
	}
}
