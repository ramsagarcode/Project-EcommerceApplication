package org.jsp.ecommaraceapp.service;

import java.util.List;
import java.util.Optional;


import org.jsp.ecommaraceapp.dao.UserDao;
import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.exception.IdNotFoundException;
import org.jsp.ecommaraceapp.exception.InvalidCredentialException;
import org.jsp.ecommaraceapp.exception.UserNotFoundException;
import org.jsp.ecommaraceapp.model.User;
import org.jsp.ecommaraceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	@Autowired
	private ECommerceAppEmailService emailService;


	public ResponseEntity<ResponseStructure<User>> saveUser(User user, HttpServletRequest request) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		user.setStatus(AccountStatus.IN_ACTIVE.toString());
		user.setToken(RandomString.make(45));
		structure.setData(userdao.saveUser(user));
		String message= emailService.sendWelcomeMail(user, request);
		structure.setMessage("User Created" + "," + message);
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> recUser = userdao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbuser = new User();
			dbuser.setName(user.getName());
			dbuser.setPhone(user.getPhone());
			dbuser.setAge(user.getAge());
			dbuser.setEmail(user.getEmail());
			dbuser.setPassword(user.getPassword());
			user = userdao.saveUser(user);
		}
		structure.setData(userdao.saveUser(user));
		structure.setMessage("User Updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userdao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		Optional<User> recUser = userdao.verifyByEmail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Email Verified");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException();
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password) {
		Optional<User> recUser = userdao.verifyByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Phone Number Verified");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException();
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<User> recUser = userdao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User user = recUser.get();
			user.setStatus(AccountStatus.ACTIVE.toString());
			user.setToken(null);
			userdao.saveUser(user);
			structure.setData("User Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

		}
		throw new UserNotFoundException("Invalid URL");
	}
	}

