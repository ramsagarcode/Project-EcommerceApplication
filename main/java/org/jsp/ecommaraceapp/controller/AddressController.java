package org.jsp.ecommaraceapp.controller;

import java.util.List;

import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.model.Address;
import org.jsp.ecommaraceapp.model.Merchant;
import org.jsp.ecommaraceapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
	@Autowired
	public AddressService addressService;
	@PostMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address,@PathVariable int user_id) {
		return addressService.saveAddress(address,user_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
		return addressService.updateAddress(address); 
}
	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		return addressService.findById(id);
	}
	@GetMapping("/{user_id}")
	public ResponseEntity<ResponseStructure<List<Address>>> findByMerchantId(@PathVariable int user_id) {
		return addressService.findAddresssByUserId(user_id);
	}
	
}