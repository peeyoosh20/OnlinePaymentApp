package com.moneytap.services;

import com.moneytap.exceptions.ItemsNotFoundException;
import com.moneytap.model.Transaction;
import com.moneytap.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> viewAllTransactions() {
        List<Transaction> transactionList=new ArrayList<>();
        transactionRepository.findAll().forEach(transaction -> transactionList.add(transaction));
        return transactionList;
    }


    @Override
    public List<Transaction> viewTransactionByDate(LocalDate date) throws ItemsNotFoundException {
        List<Transaction> transactionList=transactionRepository.findBytransactionDate(date);
        if (transactionList.size()==0){
            throw new ItemsNotFoundException("Items Not Present into List");
        }
        else {
            return transactionList;
        }

    }

    @Override
    public List<Transaction> viewTransactionByType(String type) throws ItemsNotFoundException {
        List<Transaction> transactionList=transactionRepository.findBytransactionType(type);
        if (transactionList.size()==0){
            throw new ItemsNotFoundException("Items Not Present into List");
        }
        else {
            return transactionList;
        }

    }
}
