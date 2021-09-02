package com.moneytap.repository;

import com.moneytap.model.CustomerToken;
import org.springframework.data.repository.CrudRepository;

public interface CustomerTokenRepository extends CrudRepository<CustomerToken,Integer> {
        CustomerToken findBytoken(String token);


}
