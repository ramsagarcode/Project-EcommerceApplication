package org.jsp.ecommaraceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommaraceapp.model.User;
import org.jsp.ecommaraceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> verifyByEmail(String email, String password) {
		return userRepository.verifyByEmail(email, password);
	}

	public Optional<User> verifyByPhone(long phone, String password) {
		return userRepository.verifyByPhone(phone, password);
	}
		
	public Optional<User> findByToken(String token) {
		return userRepository.findByToken(token);
	
	}

}
