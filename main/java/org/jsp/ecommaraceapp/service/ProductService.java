package org.jsp.ecommaraceapp.service;




import java.util.List;
import java.util.Optional;

import org.jsp.ecommaraceapp.dao.MerchantDao;
import org.jsp.ecommaraceapp.dao.ProductDao;
import org.jsp.ecommaraceapp.dto.ResponseStructure;
import org.jsp.ecommaraceapp.exception.MerchantNotFoundException;
import org.jsp.ecommaraceapp.exception.ProductNotFoundException;
import org.jsp.ecommaraceapp.model.Merchant;
import org.jsp.ecommaraceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(id);
		if (recProduct.isEmpty()) {
			throw new ProductNotFoundException("Invalid Product Id");
		}
		structure.setData(recProduct.get());
		structure.setMessage("Product Found");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMechant = merchantDao.findById(merchant_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recMechant.isPresent()) {
			Merchant merchant = recMechant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product Added");
			structure.setStatuscode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new MerchantNotFoundException("cannot add Product as Merchant Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(product.getId());
		if (recProduct.isEmpty()) {
			throw new ProductNotFoundException("cannot update product as Id is Invalid");
		}
		Product dbProduct = recProduct.get();
		dbProduct.setBrand(product.getBrand());
		dbProduct.setCategory(product.getCategory());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setCost(product.getCost());
		dbProduct.setImage_url(product.getImage_url());
		dbProduct.setName(product.getName());
		structure.setData(productDao.saveProduct(dbProduct));
		structure.setMessage("Product Updated");
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByMerchantId(merchant_id);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered Merchant Id");
		}
		structure.setData(products);
		structure.setMessage("List of Products for Merchant Id");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByBrand(brand);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered brand");
		}
		structure.setData(products);
		structure.setMessage("List of Products for the entered brand");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByCategory(category);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered Category");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered Category");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByName(String name) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findByName(name);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No Products Found for entered name");
		}
		structure.setData(products);
		structure.setMessage("List of Products for entered name");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> products = productDao.findAll();
		structure.setData(products);
		structure.setMessage("List of Products for Merchant Id");
		structure.setStatuscode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
}