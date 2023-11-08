package com.app.restapi.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.restapi.entity.User;

//To perform database operations for user

public interface UserRepo extends JpaRepository<User, Integer> {
		
	Optional<User> findByUsername(String username);
	
}
