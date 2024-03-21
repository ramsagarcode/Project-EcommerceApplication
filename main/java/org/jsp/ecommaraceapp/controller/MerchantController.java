package org.jsp.ecommaraceapp.controller;

import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.model.Merchant;
import org.jsp.ecommaraceapp.service.MerchantService;
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
@RequestMapping("/merchants")
@CrossOrigin
public class MerchantController {
	@Autowired
	public MerchantService merchantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant, HttpServletRequest request ) {
		return merchantService.saveMerchant(merchant, request);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable int id) {
		return merchantService.findById(id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(email, password);
	}

	@PostMapping("/verfiy-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(phone, password);
	}
		@GetMapping("/activate")
		public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token) {
			return merchantService.activate(token);
	}
}

