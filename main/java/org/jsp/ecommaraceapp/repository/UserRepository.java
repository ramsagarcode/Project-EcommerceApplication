package org.jsp.ecommaraceapp.repository;

import java.util.Optional;

import org.jsp.ecommaraceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.email=?1 and u.password=?2")
	Optional<User> verifyByEmail(String email, String password);
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	Optional<User> verifyByPhone(long phone, String password);
	
	public Optional<User> findByToken(String token);

}
