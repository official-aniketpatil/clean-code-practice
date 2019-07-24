package com.epam.engx.cleancode.errorhandling.task1.persistence.exceptions;

public class UserHomeAddressNotFoundException extends RuntimeException{
    public UserHomeAddressNotFoundException(String string) {
		super(string);
	}
}
