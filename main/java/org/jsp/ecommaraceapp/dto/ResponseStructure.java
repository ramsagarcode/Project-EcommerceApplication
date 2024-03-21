package org.jsp.ecommaraceapp.dto;

import java.util.List;

import org.jsp.ecommaraceapp.model.Product;

public class ResponseStructure<T> {
	
	private String message;
	private T data;
	private int statuscode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	

}
