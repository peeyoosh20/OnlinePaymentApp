package com.moneytap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="seq1", initialValue=6800000, allocationSize=100)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wallet {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq1")
    private int walletId;
    private double balance;

    public Wallet(int walletId, double balance) {
        this.balance = balance;
    }

    public Wallet() {
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
