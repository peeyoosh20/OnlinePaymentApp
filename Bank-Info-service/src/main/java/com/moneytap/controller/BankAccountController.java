package com.moneytap.controller;

import com.moneytap.exceptions.BankAccountNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BankAccount;
import com.moneytap.model.BankAccountList;
import com.moneytap.service.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;


    @RequestMapping(method = RequestMethod.GET,value = "/")
    public BankAccountList getAllAccounts(){
        BankAccountList bankAccountList=new BankAccountList();
        bankAccountList.setBankAccountList(bankAccountService.getAllAccounts());
        return bankAccountList;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addAccount")
    public void addBankAccount(@RequestBody BankAccount bankAccount) throws WalletIdNotFoundException {
        bankAccountService.addBankAccount(bankAccount);
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteAccount/{accountNo}")
    public void removeAccount(@PathVariable int accountNo) throws BankAccountNotFoundException {
        bankAccountService.removeAccount(accountNo);
    }
}
