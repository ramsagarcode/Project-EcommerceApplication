package org.jsp.ecommaraceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommaraceapp.dao.AddressDao;
import org.jsp.ecommaraceapp.dao.UserDao;
import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.exception.AddressNotFoundException;
import org.jsp.ecommaraceapp.model.Address;
import org.jsp.ecommaraceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
	Optional<User>recUser = userDao.findById(user_id);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		structure.setData(addressDao.saveAddress(address));
		structure.setMessage("Address saved");
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(address.getId());
		if (recAddress.isPresent()) {
			Address dbAddress = recAddress.get();
			dbAddress.setLandmark(address.getLandmark());
			dbAddress.setBuildingname(address.getBuildingname());
			dbAddress.setHousenumber(address.getHousenumber()); // Fixed typo here
			dbAddress.setCity(address.getCity());
			dbAddress.setState(address.getState());
			dbAddress.setPincode(address.getPincode());
			dbAddress.setCountry(address.getCountry());
			structure.setData(addressDao.saveAddress(dbAddress));
			structure.setMessage("address updated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AddressNotFoundException("Invalid Address ");
	}

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(id);
		if (recAddress.isPresent()) {
			structure.setData(recAddress.get());
			structure.setMessage("Address  Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new AddressNotFoundException("Invalid id");
	}

	
		public ResponseEntity<ResponseStructure<List<Address>>> findAddresssByUserId(int user_id) {
			ResponseStructure<List<Address>> structure = new ResponseStructure<>();
			List<Address> addresss = addressDao.findAddressByUserId(user_id);
			if (addresss.isEmpty()) {
				throw new AddressNotFoundException("No Addresss Found for entered User Id");
			}
			structure.setData(addresss);
			structure.setMessage("List of Addresss for User Id");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.OK);
		}


}
