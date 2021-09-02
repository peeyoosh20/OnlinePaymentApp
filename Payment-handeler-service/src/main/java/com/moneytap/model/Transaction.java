package com.moneytap.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name="seqTran", initialValue=100000, allocationSize=100)

public class Transaction {
    @Id
    @SequenceGenerator(name="seqTran", initialValue=100000, allocationSize=100)
    private int transactionId;
    private String transactionType;



    private LocalDate transactionDate;
    private double amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public Transaction(int transactionId, String transactionType, LocalDate transactionDate, double amount, String description) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

}
