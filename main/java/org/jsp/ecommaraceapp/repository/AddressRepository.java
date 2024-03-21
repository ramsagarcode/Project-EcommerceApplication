package org.jsp.ecommaraceapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommaraceapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AddressRepository extends JpaRepository<Address, Integer>{
	@Query("select a from Address a where a.user.id=?1")
	List<Address> findByUserId();




}
