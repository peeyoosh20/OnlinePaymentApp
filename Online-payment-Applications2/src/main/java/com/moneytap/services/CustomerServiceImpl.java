package com.moneytap.services;

import com.moneytap.exceptions.AlreadyCustomerAvailableException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.Customer;
import com.moneytap.repository.CustomerRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Override
    public void customerRegistration(Customer customer,int walletId) throws WalletIdNotFoundException, AlreadyCustomerAvailableException {


        AtomicBoolean flag = new AtomicBoolean(false);
        customerRepository.findAll().forEach(customer1 -> {

            if(customer1.getWallet().getWalletId()==walletId){

                flag.set(true);
            }});

        if (walletRepository.existsById(walletId)) {

            if(flag.get()) {
                throw new AlreadyCustomerAvailableException("Customer Already Registered to wallet Id "+walletId+"  !!");

            }
            else{
                customerRepository.save(customer);

            }
        }
        else {
            throw new WalletIdNotFoundException("Invalid Wallet Id !!");
        }

    }


}
