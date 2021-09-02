package com.moneytap.services;

import com.moneytap.exceptions.BillIdNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BillPayment;
import com.moneytap.repository.BillPaymentRepository;
import com.moneytap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillPaymentServiceImpl implements BillPaymentService{
    @Autowired
    private BillPaymentRepository billPaymentRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void addBill(BillPayment billPayment,int walletId) throws WalletIdNotFoundException {
        if(walletRepository.existsById(walletId)) {
            billPaymentRepository.save(billPayment);
        }else {
            throw new WalletIdNotFoundException("Invalid Wallet Account");
        }


    }

    @Override
    public List<BillPayment> viewAllBills() {
        List<BillPayment> billPaymentList=new ArrayList<>();
        billPaymentRepository.findAll().forEach(billPayment -> billPaymentList.add(billPayment));
        return billPaymentList;
    }

    @Override
    public BillPayment viewBill(int billId) throws BillIdNotFoundException {

        if (billPaymentRepository.existsById(billId)){
            return billPaymentRepository.findById(billId).get();

        }
        throw new BillIdNotFoundException("Invalid Bill Id !!");
    }
}
