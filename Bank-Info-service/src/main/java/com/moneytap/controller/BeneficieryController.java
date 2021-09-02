package com.moneytap.controller;

import com.moneytap.exceptions.BenefecieryNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BeneficiaryList;
import com.moneytap.model.BeneficieryDetails;
import com.moneytap.service.BeneficieryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiery")
public class BeneficieryController {

    @Autowired
    private BeneficieryService beneficieryService;
    @GetMapping("/")
    public BeneficiaryList getAllBenefecieries(){
        BeneficiaryList beneficiaryList=new BeneficiaryList();
        beneficiaryList.setBeneficieryDetailsList(beneficieryService.getAllBenefecieries());
        return beneficiaryList;

    }
    @GetMapping("/{beneficieryId}")
    public BeneficieryDetails viewBenefecierayDetails(@PathVariable int beneficieryId) throws BenefecieryNotFoundException {
        return beneficieryService.viewBenefecierayDetails(beneficieryId);

    }
    @DeleteMapping("/{beneficieryId}")
    public  void deleteBenefecieraryDetails(@PathVariable int beneficieryId) throws BenefecieryNotFoundException {
        beneficieryService.deleteBenefecieraryDetails( beneficieryId);

    }
    @PostMapping("/addBeneficiery")
    public void addBenefecierary(@RequestBody BeneficieryDetails beneficieryDetails) throws WalletIdNotFoundException {
        beneficieryService.addBenefecierary(beneficieryDetails);


    }
}
