package com.moneytap.service;

import com.moneytap.exceptions.BankAccountNotFoundException;
import com.moneytap.model.BankAccount;
import com.moneytap.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Override
    public List<BankAccount> getAllAccounts() {
        List<BankAccount> bankAccountList=new ArrayList<>();
        bankAccountRepository.findAll().forEach(bankAccount -> bankAccountList.add(bankAccount));
        return bankAccountList;
    }

    @Override
    public void addBankAccount(BankAccount bankAccount)  {

            bankAccountRepository.save(bankAccount);

    }

    @Override
    public void removeAccount(int bankAccount) throws BankAccountNotFoundException {
        if (bankAccountRepository.existsById(bankAccount)){
            bankAccountRepository.deleteById(bankAccount);

        }else {
            throw new BankAccountNotFoundException("Invalid Bank Account Details");
        }

    }
}
