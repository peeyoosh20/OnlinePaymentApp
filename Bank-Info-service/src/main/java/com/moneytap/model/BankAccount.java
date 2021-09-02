package com.moneytap.model;


import javax.persistence.*;

@Entity
@SequenceGenerator(name="seq", initialValue=6000000, allocationSize=100)
public class BankAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int accountNo;
    private String IFSCcode;
    private String bankName;
    private double balance;

    @OneToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public BankAccount(String IFSCcode, String bankName, double balance) {
        this.IFSCcode = IFSCcode;
        this.bankName = bankName;
        this.balance = balance;
    }

    public BankAccount() {
    }


    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFSCcode() {
        return IFSCcode;
    }

    public void setIFSCcode(String IFSCcode) {
        this.IFSCcode = IFSCcode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
