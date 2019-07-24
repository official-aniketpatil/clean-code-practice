package com.epam.engx.cleancode.errorhandling.task1.persistence.exceptions;

public class UserDeliveryAddressNotFoundException extends RuntimeException {
  
	public UserDeliveryAddressNotFoundException(String message) {
	 super(message);
	}
}
