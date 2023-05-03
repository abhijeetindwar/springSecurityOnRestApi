package com.securityDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.securityDemo.dto.Product;
import com.securityDemo.entities.UserInfo;
import com.securityDemo.repository.UserRepo;

@Service
public class ProductService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder encoder;

	public List<Product> productList() {
		// TODO Auto-generated method stub
		List<Product> list=new ArrayList<>();
		list.add(Product.builder().productId(1).productName("book").price("4500").build());
		list.add(Product.builder().productId(2).productName("food").price("700").build());
		list.add(Product.builder().productId(3).productName("drinks").price("500").build());
		list.add(Product.builder().productId(4).productName("cloths").price("3800").build());
		
		return list ;
	}

	public Product findById(int id) {
		// TODO Auto-generated method stubList<Product> list=new ArrayList<>();
		List<Product> list=new ArrayList<>();
		list.add(Product.builder().productId(1).productName("book").price("4500").build());
		list.add(Product.builder().productId(2).productName("food").price("700").build());
		list.add(Product.builder().productId(3).productName("drinks").price("500").build());
		list.add(Product.builder().productId(4).productName("cloths").price("3800").build());
		Product p=new Product();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getProductId()==id) {
				p=list.get(i);
				break;
			}
		}
		return p;
	}
	public String addUser(UserInfo user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User Added Successfully";
	}

}
