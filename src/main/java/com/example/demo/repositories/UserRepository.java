package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.EmailDTO;
import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

	User findByUsername(String username);

	User findByEmail(EmailDTO emailAddress);

}
