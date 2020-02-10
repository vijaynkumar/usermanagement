package com.kristal.user.management.service;

import java.util.List;

import com.kristal.user.management.commons.request.AuthRequest;
import com.kristal.user.management.dao.entity.UserEntity;

public interface IUserService {
	
	UserEntity craeteUser(UserEntity userRequest);
	List<UserEntity> getUsers();
	UserEntity updateUser(UserEntity userRequest);
	UserEntity getUserById(Long id);
	boolean resetPassword(AuthRequest request);
	boolean login(AuthRequest request);
}
