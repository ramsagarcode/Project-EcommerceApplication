package org.jsp.ecommaraceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommaraceapp.model.Address;
import org.jsp.ecommaraceapp.model.Product;
import org.jsp.ecommaraceapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.servlet.http.HttpServletRequest;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;

	public Address saveAddress(Address address) {
		return addressRepository.save(address);

	}

	public Optional<Address> findById(int id) {
		return addressRepository.findById(id);

	}

	public List<Address> findAddressByUserId(int user_id) {

		return addressRepository.findByUserId();
	}

}