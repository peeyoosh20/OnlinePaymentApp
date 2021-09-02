package com.moneytap.repository;

import com.moneytap.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount,Integer> {
}
