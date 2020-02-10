package com.kristal.user.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristal.user.management.common.util.PasswordUtil;
import com.kristal.user.management.commons.request.AuthRequest;
import com.kristal.user.management.dao.entity.UserEntity;
import com.kristal.user.management.dao.repository.UserRepository;
import com.kristal.user.management.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserEntity craeteUser(UserEntity userRequest) {

		if (null != userRequest) {
			String password = PasswordUtil.generatePasword();
			userRequest.setPassword(password);
			repository.save(userRequest);
		}
		return userRequest;
	}

	@Override
	public List<UserEntity> getUsers() {
		return repository.findAll();
	}

	@Override
	public UserEntity updateUser(UserEntity userRequest) {
		Optional<UserEntity> user = repository.findByUserId(userRequest.getUserId());
		if (user.isPresent()) {
			UserEntity userEntity = user.get();
			userEntity.setDob(userRequest.getDob());
			userEntity.setEmail(userRequest.getEmail());
			userEntity.setFirstName(userRequest.getFirstName());
			userEntity.setLastName(userRequest.getLastName());
			repository.save(userEntity);
			return userEntity;

		}
		return null;
	}

	@Override
	public UserEntity getUserById(Long id) {
		if (null == id) {
			return null;
		}
		Optional<UserEntity> user = repository.findByUserId(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public boolean resetPassword(AuthRequest request) {
		if (null != request) {
			Optional<UserEntity> user = repository.findByUserIdAndPassword(request.getUserId(),
					request.getOldPassword());
			if (user.isPresent()) {
				UserEntity userEntity = user.get();
				userEntity.setPassword(request.getNewPassword());
				repository.save(userEntity);
				return true;
			}
		}

		return false;
	}
	

	@Override
	public boolean login(AuthRequest request) {
		if (null != request) {
			Optional<UserEntity> user = repository.findByUserIdAndPassword(request.getUserId(), request.getPassword());
			if (user.isPresent()) {
				return true;
			}
		}
		return false;
	}

}
