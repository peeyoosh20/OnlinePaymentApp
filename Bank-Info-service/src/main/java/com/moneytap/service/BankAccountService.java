package com.moneytap.service;

import com.moneytap.exceptions.BankAccountNotFoundException;
import com.moneytap.model.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> getAllAccounts();
    void addBankAccount(BankAccount bankAccount) ;
    void removeAccount(int bankAccount) throws BankAccountNotFoundException;
}
