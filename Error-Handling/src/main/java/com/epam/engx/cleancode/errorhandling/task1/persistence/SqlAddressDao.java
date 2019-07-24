package com.epam.engx.cleancode.errorhandling.task1.persistence;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;
import com.epam.engx.cleancode.errorhandling.task1.AddressDao;
import com.epam.engx.cleancode.errorhandling.task1.persistence.exceptions.UserDeliveryAddressNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.persistence.exceptions.UserHomeAddressNotFoundException;
import com.epam.engx.cleancode.errorhandling.task1.persistence.thirdpartyjar.SqlService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlAddressDao implements AddressDao {

    SqlService sqlService;

    public Address getHomeAddress(String userId) {
    	try {
    		return new Address(sqlService.queryUserHomeAddress(userId));
        }catch(SQLException e) {
        	throw new UserHomeAddressNotFoundException("home address not found");
        }
    }

    public List<Address> getDeliveryAddresses(String userId) {
        try {
	    	List<Address> addresses = new ArrayList<Address>();
	        for (Map addressData : sqlService.queryUserDeliveryAddress(userId)) {
	            addresses.add(new Address(addressData));
	        }
	        return addresses;
        }catch(SQLException e) {
        	throw new UserDeliveryAddressNotFoundException("delivery address not found");
        }
    }
}
