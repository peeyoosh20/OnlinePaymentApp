package com.moneytap.services;

import com.moneytap.exceptions.ItemsNotFoundException;
import com.moneytap.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<Transaction> viewAllTransactions();
    List<Transaction> viewTransactionByDate(LocalDate date) throws ItemsNotFoundException;
    List<Transaction> viewTransactionByType(String type) throws ItemsNotFoundException;

}
