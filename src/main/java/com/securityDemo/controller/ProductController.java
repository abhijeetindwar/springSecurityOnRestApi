package com.securityDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securityDemo.dto.Product;
import com.securityDemo.entities.UserInfo;
import com.securityDemo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
 private ProductService service;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to the home Page. this end point is not secured";
	}
	@PostMapping("/add")
	public String addUser(@RequestBody UserInfo user) {
		return service.addUser(user);
		
	}
	@GetMapping("/list")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> show(){
		return service.productList();
	}
	@GetMapping("/{id}")
//	 @PreAuthorize("hasAuthority('ROLE_USER')")

	public Product showById(@PathVariable int id) {
		System.out.println("here the id is :"+id);
		return service.findById(id);
	}
	
}
