package com.moneytap.services;
import com.moneytap.exceptions.*;
import com.moneytap.model.Customer;
import com.moneytap.model.Wallet;

import java.util.List;

public interface WalletService {
    void createWallet(  Wallet wallet) throws BankAccountNotFoundException;
    List<Customer> getCustomer();
    Double getBalance(int walletId) throws CustomerNotFoundException, WalletIdNotFoundException;
    void changePassword(int customerId,String oldPassword,String newPassword,String conformPassword) throws InvalidUserNameAndPasswordException, CustomerNotFoundException;
    void addMoneyInWallet(int walletId, double amount, int accountNo) throws BankAccountNotFoundException, WalletIdNotFoundException, InsufficientBalanceException;
    void fundTransfer(int userWalletId,String benMobileNo,double amount) throws BenefecieryNotFoundException, InsufficientBalanceException, WalletIdNotFoundException;
    void depositAmount(int walletId,double amount,int accountNo) throws WalletIdNotFoundException, BankAccountNotFoundException, InsufficientBalanceException;
    void billPayment(int walletId, double amount) throws WalletIdNotFoundException, InsufficientBalanceException;
}
