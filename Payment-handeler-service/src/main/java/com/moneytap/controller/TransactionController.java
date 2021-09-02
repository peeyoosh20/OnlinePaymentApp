package com.moneytap.controller;

import com.moneytap.exceptions.ItemsNotFoundException;
import com.moneytap.model.TransactionCollectList;
import com.moneytap.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/allTransaction")
    public TransactionCollectList viewAllTransactions(){
        TransactionCollectList transactionCollectList=new TransactionCollectList();
        transactionCollectList.setTransactionList(transactionService.viewAllTransactions());

       return transactionCollectList;

    }
    @GetMapping("transactionByDate/{date}")
    public TransactionCollectList viewTransactionByDate(@PathVariable  String date) throws ItemsNotFoundException {
        LocalDate localDate=LocalDate.parse(date);
        TransactionCollectList transactionCollectList=new TransactionCollectList();
        transactionCollectList.setTransactionList(transactionService.viewTransactionByDate(localDate));
        return transactionCollectList;

    }
    @GetMapping("/translationByType/{type}")
    public TransactionCollectList viewTransactionByType(@PathVariable String type) throws ItemsNotFoundException {

        TransactionCollectList transactionCollectList=new TransactionCollectList();
        transactionCollectList.setTransactionList(transactionService.viewTransactionByType(type));
        return transactionCollectList;

    }
}
