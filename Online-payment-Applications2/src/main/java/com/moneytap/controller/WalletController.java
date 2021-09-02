package com.moneytap.controller;

import com.moneytap.exceptions.*;
import com.moneytap.model.*;
import com.moneytap.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public void createWallet(@RequestBody Wallet wallet) throws BankAccountNotFoundException {
        walletService.createWallet(wallet);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{walletId}")
    public double getBalance(@PathVariable int walletId) throws CustomerNotFoundException, WalletIdNotFoundException {
        return walletService.getBalance(walletId);
    }

    @GetMapping("/admin")
    public List<Customer> getCustomer(){
       return walletService.getCustomer();
    }

    @PostMapping("/password/{customerId}/{oldPassword}/{newPassword}/{conformPassword}")
    public  void changePassword(@PathVariable  int customerId,@PathVariable String oldPassword,@PathVariable String newPassword,@PathVariable String conformPassword) throws InvalidUserNameAndPasswordException, CustomerNotFoundException {
        walletService.changePassword(customerId,oldPassword,newPassword,conformPassword);

    }
    @PostMapping("/addAmount/{walletId}/{amount}/{accountNo}")
    public void addMoneyInWallet(@PathVariable int walletId,@PathVariable double amount,@PathVariable int accountNo) throws BankAccountNotFoundException, InsufficientBalanceException, WalletIdNotFoundException {
        walletService.addMoneyInWallet(walletId,amount,accountNo);


    }
    @PostMapping("/fundTransferToBeneficiary/{userWalletId}/{benMobileNo}/{amount}")
   public void fundTransfer(@PathVariable int userWalletId, @PathVariable String benMobileNo, @PathVariable double amount) throws BenefecieryNotFoundException, InsufficientBalanceException, WalletIdNotFoundException {
            walletService.fundTransfer(userWalletId,benMobileNo,amount);
   }
   @PostMapping("/deposit/{walletId}/{amount}/{accountNo}")
    public void depositAmount(@PathVariable int walletId,@PathVariable double amount,@PathVariable int accountNo) throws BankAccountNotFoundException, WalletIdNotFoundException, InsufficientBalanceException {
        walletService.depositAmount(walletId,amount,accountNo);


    }


    @PostMapping("/billPay/{walletId}/{amount}")
    public void billPayment(@PathVariable int walletId,@PathVariable double amount) throws WalletIdNotFoundException, InsufficientBalanceException {
        walletService.billPayment(walletId,amount);
    }


    // Beneficiary Controller Start from here

    @GetMapping("/beneficiery/{beneficieryId}")
    public BeneficieryDetails viewBenefecierayDetails(@PathVariable int beneficieryId)  {
        String benefciaryUrl="http://bank-info-service/beneficiery/";
        BeneficieryDetails beneficieryDetails=restTemplate.getForObject(benefciaryUrl+beneficieryId,BeneficieryDetails.class);
        return beneficieryDetails;
    }


    @PostMapping("/addBeneficiery")
    public void addBenefecierayDetails(@RequestBody BeneficieryDetails beneficieryDetails)  {
        String benefciaryUrl="http://bank-info-service/beneficiery/addBeneficiery";

    restTemplate.postForObject(benefciaryUrl,beneficieryDetails,BeneficieryDetails.class);

    }

    @GetMapping("/allBeneficiery")
    public List<BeneficieryDetails> getAllBenefecieries(){
        String benefciaryUrl="http://bank-info-service/beneficiery/";
        BeneficiaryList beneficiaryList=restTemplate.getForObject(benefciaryUrl,BeneficiaryList.class);
        return beneficiaryList.getBeneficieryDetailsList();

    }




   //  Bank controller Start From Here

    @RequestMapping(method = RequestMethod.POST,value = "/addAccount")
    public void addBankAccount(@RequestBody BankAccount bankAccount)  {

        String bankUrl="http://bank-info-service/bank/addAccount/";
        restTemplate.postForObject(bankUrl,bankAccount,BankAccount.class);


    }


    @RequestMapping(method = RequestMethod.GET,value = "/allBankAccount")
    public List<BankAccount> getAllAccounts(){
        String bankUrl="http://bank-info-service/bank/";
        BankAccountList bankAccountList=restTemplate.getForObject(bankUrl,BankAccountList.class);
        return bankAccountList.getBankAccountList();
    }


    // Bill Handling Controller

    @GetMapping("/allBill")
    public List<BillPayment> viewAllBills() {
        String billUrl="http://payment-handler-info-service/bill/";
        BillPaymentList billPaymentList=restTemplate.getForObject(billUrl,BillPaymentList.class);
        return billPaymentList.getBillPaymentList();
    }

    @PostMapping("/addBill/{walletId}")
    public void addBill(@ RequestBody BillPayment billPayment,@PathVariable int walletId) throws WalletIdNotFoundException {
        String billUrl="http://payment-handler-info-service/bill/addBill/";
        restTemplate.postForObject(billUrl+walletId,billPayment,BillPayment.class);

    }

    @GetMapping("/getBillById/{billId}")
    public BillPayment viewBill(@PathVariable int billId) throws BillIdNotFoundException {
        String billUrl="http://payment-handler-info-service/bill/";
        BillPayment billPayment=restTemplate.getForObject(billUrl,BillPayment.class);
        return billPayment;

    }


    // Transaction Controller

    @GetMapping("/allTransaction")
    public List<Transaction> viewAllTransactions(){
        String transUrl="http://payment-handler-info-service/transaction/allTransaction/";
        TransactionCollectList transactionCollectList=restTemplate.getForObject(transUrl,TransactionCollectList.class);
        return transactionCollectList.getTransactionList();
    }


    @GetMapping("transactionByDate/{date}")
    public List<Transaction> viewTransactionByDate(@PathVariable  String date) throws ItemsNotFoundException {
        String transUrl="http://payment-handler-info-service/transaction/transactionByDate/";
        TransactionCollectList transactionCollectList=restTemplate.getForObject(transUrl+date,TransactionCollectList.class);
        return transactionCollectList.getTransactionList();


    }
    @GetMapping("/translationByType/{type}")
    public List<Transaction> viewTransactionByType(@PathVariable String type) throws ItemsNotFoundException {
        String transUrl="http://payment-handler-info-service/transaction/translationByType/";
        TransactionCollectList transactionCollectList=restTemplate.getForObject(transUrl+type,TransactionCollectList.class);
        return transactionCollectList.getTransactionList();

    }



}
