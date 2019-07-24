package com.epam.engx.cleancode.errorhandling.task1.persistence.exceptions;

public class UserOrderAddressNotFoundException extends RuntimeException{
	public UserOrderAddressNotFoundException(String message) {
		super(message);
	}
}
