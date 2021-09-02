package com.moneytap.services;

import com.moneytap.exceptions.BillIdNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BillPayment;

import java.util.List;

public interface BillPaymentService {
    void addBill(BillPayment billPayment,int walletId) throws WalletIdNotFoundException;
    List<BillPayment> viewAllBills();
    BillPayment viewBill(int billId) throws BillIdNotFoundException;

}
