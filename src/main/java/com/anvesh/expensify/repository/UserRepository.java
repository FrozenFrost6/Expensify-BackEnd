package com.anvesh.expensify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anvesh.expensify.model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	public Optional<User> findByUsername(String username);
	
	public void deleteByUsername(String username);
}
