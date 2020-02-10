package com.kristal.user.management.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kristal.user.management.commons.request.AuthRequest;
import com.kristal.user.management.dao.entity.UserEntity;
import com.kristal.user.management.service.IUserService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserEntity userRequest){
		UserEntity user = service.craeteUser(userRequest);
		return ResponseEntity.ok(user);	
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser(){
		List<UserEntity> user = service.getUsers();
		return ResponseEntity.ok(user);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser(@PathVariable("id") Long id){
		UserEntity user = service.getUserById(id);
		if(null == user) {
			return ResponseEntity.ok("user not found");	
		}
		return ResponseEntity.ok(user);	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody UserEntity userRequest){
		UserEntity user = service.updateUser(userRequest);
		if(null == user) {
			return ResponseEntity.ok("user not found. updation failed");	
		}
		return ResponseEntity.ok(user);	
	}
	
	@RequestMapping(value = "/reset/password", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestBody AuthRequest userRequest){
		boolean reseted = service.resetPassword(userRequest);
		String message = "password changed succesfully";
		if(!reseted)
		{
			message = "password changed failed";
		}
		return ResponseEntity.ok(message);	
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody AuthRequest userRequest){
		boolean logedin = service.login(userRequest);
		String message = "login successfully done";
		if(!logedin) {
			message = "login failed";
		}
		return ResponseEntity.ok(message);	
	}


}
