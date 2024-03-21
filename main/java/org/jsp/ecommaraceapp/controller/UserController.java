package org.jsp.ecommaraceapp.controller;

import java.util.List;

import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.model.User;
import org.jsp.ecommaraceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	@Autowired
	public UserService userService;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveMerchant(@RequestBody User user, HttpServletRequest request) {
		return userService.saveUser(user, request);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateMerchant(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
		return userService.findById(id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam(name="email") String email,
			@RequestParam(name="password") String password) {
		return userService.verifyUser(email, password);
	}

	@PostMapping("/verfiy-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam(name="phone") long phone,
			@RequestParam(name="password") String password) {
		return userService.verifyUser(phone, password);
		
	}
	@GetMapping("/activate")
	public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token) {
		return userService.activate(token);
	

}
}