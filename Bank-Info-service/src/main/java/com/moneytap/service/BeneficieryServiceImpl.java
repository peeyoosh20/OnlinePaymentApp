package com.moneytap.service;

import com.moneytap.exceptions.BenefecieryNotFoundException;
import com.moneytap.model.BeneficieryDetails;
import com.moneytap.repository.BeneficieryDetailsRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BeneficieryServiceImpl implements BeneficieryService{

    @Autowired
    private BeneficieryDetailsRepository beneficieryDetailsRepository;
    @Autowired
   private WalletRepository walletRepository;

    @Override
    public List<BeneficieryDetails> getAllBenefecieries() {
        List<BeneficieryDetails> beneficieryList=new ArrayList<>();
        beneficieryDetailsRepository.findAll().forEach(beneficieryDetails -> beneficieryList.add(beneficieryDetails));
        return beneficieryList;
    }

    @Override
    public BeneficieryDetails viewBenefecierayDetails(int beneficieryId) throws BenefecieryNotFoundException {

        if (beneficieryDetailsRepository.existsById(beneficieryId)){
            return beneficieryDetailsRepository.findById(beneficieryId).get();

        }else {
            throw new BenefecieryNotFoundException("Invalid Beneficiary Details!!");
        }
    }

    @Override
    public void deleteBenefecieraryDetails(int beneficieryId) throws BenefecieryNotFoundException {
        if (beneficieryDetailsRepository.existsById(beneficieryId)){
            beneficieryDetailsRepository.deleteById(beneficieryId);

        }else {
            throw new BenefecieryNotFoundException("Invalid Beneficiary Details!!");
        }

    }

    @Override
    public void addBenefecierary(BeneficieryDetails beneficieryDetails)  {
            beneficieryDetailsRepository.save(beneficieryDetails);


    }
}
