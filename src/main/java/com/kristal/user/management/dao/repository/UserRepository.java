package com.kristal.user.management.dao.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristal.user.management.dao.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByUserId(Long userId);
	Optional<UserEntity> findByUserIdAndPassword(Long userId, String password);
}

