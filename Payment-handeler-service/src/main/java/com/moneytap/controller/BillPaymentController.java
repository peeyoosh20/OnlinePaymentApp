package com.moneytap.controller;

import com.moneytap.exceptions.BillIdNotFoundException;
import com.moneytap.exceptions.WalletIdNotFoundException;
import com.moneytap.model.BillPayment;
import com.moneytap.model.BillPaymentList;
import com.moneytap.services.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillPaymentController {
    @Autowired
    private BillPaymentService billPaymentService;


    @GetMapping("/")
   public BillPaymentList viewAllBills() {
       BillPaymentList billPaymentList=new BillPaymentList();
       billPaymentList.setBillPaymentList(billPaymentService.viewAllBills());
        return billPaymentList;
   }

  @PostMapping("/addBill/{walletId}")
   public void addBill(@RequestBody BillPayment billPayment,@PathVariable int walletId) throws WalletIdNotFoundException {
       billPaymentService.addBill(billPayment,walletId);

   }

   @GetMapping("/{billId}")
    public BillPayment viewBill(@PathVariable int billId) throws BillIdNotFoundException {
       return billPaymentService.viewBill(billId);

    }
}
