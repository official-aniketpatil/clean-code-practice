package com.epam.engx.cleancode.functions.task3;

import com.epam.cleancode.function.exception.InvalidCredentialException;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.SessionManager;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.UserService;

public abstract class UserAuthenticator implements UserService {

    private SessionManager sessionManager;

    public User login(String userName, String password) {
        return loginUser(getUserByName(userName), password);
    }

    private User loginUser(User user, String password) {
    	validatePassword(user, password);
    	initiateSession(user);
    	
    	return user;
    }
    
    private void validatePassword(User user, String password) {
   	 	if (isPasswordNotCorrect(user, password)) {
            throw new InvalidCredentialException(" Username or Password are incorrect");
        }
    }
    
    private boolean isPasswordNotCorrect(User user, String password) {
    	return !isPasswordCorrect(user, password);
    }
    
    private void initiateSession(User user) {
    	sessionManager.setCurrentUser(user);
    }
    
}
