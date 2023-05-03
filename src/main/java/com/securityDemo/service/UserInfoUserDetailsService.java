package com.securityDemo.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.securityDemo.UserInfoUserDetails;
import com.securityDemo.entities.UserInfo;

import com.securityDemo.repository.UserRepo;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
    private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo=userRepo.findByName(username);
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found "+username));

		
		
	}

}
