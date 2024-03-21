package org.jsp.ecommaraceapp.service;

import java.util.Optional;

import org.jsp.ecommaraceapp.ECommaraceAppApplication;
import org.jsp.ecommaraceapp.dao.MerchantDao;
import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.exception.MerchantNotFoundException;
import org.jsp.ecommaraceapp.model.Merchant;
import org.jsp.ecommaraceapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private ECommerceAppEmailService emailService;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setData(merchantDao.saveMerchant(merchant));
		String message=emailService.sendWelcomeMail(merchant, request);
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant dbmerchant = new Merchant();

			dbmerchant.setName(merchant.getName());
			dbmerchant.setPhone(merchant.getPhone());
			dbmerchant.setGst_number(merchant.getGst_number());
			dbmerchant.setEmail(merchant.getEmail());
			dbmerchant.setPassword(merchant.getPassword());
			merchant = merchantDao.saveMerchant(merchant);

		}

		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setMessage("Merchant Updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByEmail(String email, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant email verified");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyByPhone(long phone, String password) {
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant phone number verified");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		return null;

}

	
		public ResponseEntity<ResponseStructure<String>> activate(String token) {
			Optional<Merchant> recMerchant = merchantDao.findByToken(token);
			ResponseStructure<String> structure = new ResponseStructure<>();
			if (recMerchant.isPresent()) {
				Merchant merchant = recMerchant.get();
				merchant.setStatus(AccountStatus.ACTIVE.toString());
				merchant.setToken(null);
				merchantDao.saveMerchant(merchant);
				structure.setData("Merchant Found");
				structure.setMessage("Account Verified and Activated");
				structure.setStatuscode(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);

			}
			throw new MerchantNotFoundException("Invalid URL");
	}

		public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
			if (recMerchant.isPresent()) {
				structure.setData(recMerchant.get());
				structure.setMessage("Merchant Found");
				structure.setStatuscode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
			}
			throw new MerchantNotFoundException("Invalid Phone Number or password");
		}

		public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
			ResponseStructure<Merchant> structure = new ResponseStructure<>();
			Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
			if (recMerchant.isPresent()) {
				structure.setData(recMerchant.get());
				structure.setMessage("Merchant Found");
				structure.setStatuscode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
			}
			throw new MerchantNotFoundException("Invalid Phone Number or password");
		}

		}


