package com.moneytap.repository;

import com.moneytap.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
    List<Transaction> findBytransactionDate(LocalDate date);
    List<Transaction> findBytransactionType(String type);

}
