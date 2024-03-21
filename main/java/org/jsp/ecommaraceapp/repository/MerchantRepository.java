package org.jsp.ecommaraceapp.repository;

import java.util.Optional;

import org.jsp.ecommaraceapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	Optional<Merchant> verifyMerchant(String email, String password);
	
	 
	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	Optional<Merchant> verifyMerchant(long phone, String password);

	public Optional<Merchant> findByToken(String token);
	
	

	
	
	

}
