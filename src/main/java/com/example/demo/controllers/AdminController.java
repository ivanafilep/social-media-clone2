package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.implementation.AdminServiceImpl;

@RestController
@RequestMapping(path = "project/admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@PostMapping
	public ResponseEntity<?> createAdmin(@Valid @RequestBody UserDTO newUser) {
		return adminService.createAdmin(newUser);
	}

	@PutMapping
	public ResponseEntity<?> updateAdmin(@Valid @RequestBody UserDTO updatedAdmin, @PathVariable Integer id) {
		return adminService.updateAdmin(updatedAdmin, id);
	}

}
