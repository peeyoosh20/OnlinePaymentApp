package com.moneytap.services;

import com.moneytap.exceptions.AlreadyCustomerAvailableException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.Customer;

public interface CustomerService {
    void customerRegistration(Customer customer,int walletId) throws WalletIdNotFoundException, AlreadyCustomerAvailableException;
}
