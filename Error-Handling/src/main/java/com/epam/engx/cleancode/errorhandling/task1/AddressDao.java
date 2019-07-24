package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {

    Address getHomeAddress(String userId);

    List<Address> getDeliveryAddresses(String userId);

}
