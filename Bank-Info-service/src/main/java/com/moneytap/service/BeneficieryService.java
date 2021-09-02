package com.moneytap.service;

import com.moneytap.exceptions.BenefecieryNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BeneficieryDetails;

import java.util.List;

public interface BeneficieryService {
    List<BeneficieryDetails> getAllBenefecieries();
    BeneficieryDetails viewBenefecierayDetails(int beneficieryId) throws BenefecieryNotFoundException;
    void deleteBenefecieraryDetails(int beneficieryId) throws BenefecieryNotFoundException;
    void addBenefecierary(BeneficieryDetails beneficieryDetails) throws WalletIdNotFoundException;
}
