package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date birthDate;

	private String userName;

	private boolean admin;

	private User[] subordinates;

	private int userRating;

	public User(String userName) {
		
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [Birth Date=" + birthDate + ", User Name=" + userName + ", Admin=" + admin + ", Subordinates="
				+ Arrays.toString(subordinates) + ", User Rating=" + userRating + "]";
	}

}
