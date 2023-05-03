package com.securityDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securityDemo.entities.UserInfo;


public interface UserRepo extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByName(String username);

}
