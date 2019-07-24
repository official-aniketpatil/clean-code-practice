package com.epam.engx.cleancode.functions.task3;

import com.epam.cleancode.function.exception.InvalidCredentialException;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
    	try {
        	User user = userAuthenticator.login(userName, password);
        	generateSuccessLoginResponse(userName);
        } catch(InvalidCredentialException invalidCredentialException) {
        	generateFailLoginResponse();
        }
    }
}
