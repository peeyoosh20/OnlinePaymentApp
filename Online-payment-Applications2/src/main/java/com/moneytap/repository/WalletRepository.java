package com.moneytap.repository;

import com.moneytap.model.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet,Integer> {
}
