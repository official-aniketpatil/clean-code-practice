package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    private boolean validateAccountName(Account account) {
    	if (account.getName().length() <= 5){
            throw new WrongAccountNameException();
        }
    	return true;
    }
    private boolean validateAccountPassword(Account account) {
    	String password = account.getPassword();
        if (password.length() <= 8) {
            if (passwordChecker.validate(password) != OK) {
                throw new WrongPasswordException();
            }
        }
        return true;
    }
    
    private List<Address> getAddresses(Account account) {
    	List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
        return addresses;
    }
    private void setDate(Account account) {
    	account.setCreatedDate(new Date());
    }
    public void create(Account account) {
    
    		validateAccountName(account);
    		validateAccountPassword(account);
    	    setDate(account);
    	    List<Address> addresses = getAddresses(account); 
    		account.setAddresses(addresses);
    		accountManager.createNewAccount(account);
    	
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

}
