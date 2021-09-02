package com.moneytap.repository;

import com.moneytap.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
//    Customer findByWallet_Id(int walletId);
    Customer findByname(String name);

}
