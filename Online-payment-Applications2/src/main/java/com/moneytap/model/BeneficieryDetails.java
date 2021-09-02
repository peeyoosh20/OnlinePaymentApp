package com.moneytap.model;

import javax.persistence.*;

@Entity
public class BeneficieryDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int beneficieryId;
    private String name;
    private String mobileNo;
    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public BeneficieryDetails(int beneficieryId, String name, String mobileNo) {
        this.beneficieryId = beneficieryId;
        this.name = name;
        this.mobileNo = mobileNo;
    }

    public BeneficieryDetails() {
    }

    public int getBeneficieryId() {
        return beneficieryId;
    }

    public void setBeneficieryId(int beneficieryId) {
        this.beneficieryId = beneficieryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "BeneficieryDetails{" +
                "beneficieryId=" + beneficieryId +
                ", name='" + name + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
