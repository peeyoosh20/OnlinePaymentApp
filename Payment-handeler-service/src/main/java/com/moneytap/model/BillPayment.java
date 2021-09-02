package com.moneytap.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class BillPayment {
    @Id
    private int billId;
    private String BillType;
    private double amount;

    private LocalDate paymentDate;
    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public BillPayment(int billId, String billType, double amount, LocalDate paymentDate) {
        this.billId = billId;
        BillType = billType;
        this.amount = amount;
        this.paymentDate = paymentDate;

    }

    public BillPayment() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String billType) {
        BillType = billType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "BillPayment{" +
                "billId=" + billId +
                ", BillType='" + BillType + '\'' +
                ", amount=" + amount +
                ", paymentDate='" + paymentDate + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
