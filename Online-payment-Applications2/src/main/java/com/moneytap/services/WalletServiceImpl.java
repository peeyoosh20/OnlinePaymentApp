package com.moneytap.services;
import com.moneytap.exceptions.*;
import com.moneytap.model.*;
import com.moneytap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WalletServiceImpl implements WalletService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private BeneficieryDetailsRepository beneficieryDetailsRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BillPaymentRepository billPaymentRepository;


    @Override
    public void createWallet(  Wallet wallet)  {
            walletRepository.save(wallet);

    }

    @Override
    public List<Customer> getCustomer() {
        List<Customer> customerList=new ArrayList<>();
        customerRepository.findAll().forEach(customer -> customerList.add(customer));
        return customerList;

    }

    @Override
    public Double getBalance(int walletId) throws CustomerNotFoundException, WalletIdNotFoundException {
        if(walletRepository.existsById(walletId)) {

            return walletRepository.findById(walletId).get().getBalance();
        }else {
            throw new WalletIdNotFoundException("Invalid Wallet Details !!");
        }

    }

    @Override
    public void changePassword(int customerId, String oldPassword, String newPassword,String conformPassword) throws InvalidUserNameAndPasswordException, CustomerNotFoundException {

        if (customerRepository.existsById(customerId)){
            Customer customer=customerRepository.findById(customerId).get();

            if( customer.getPassword().equals(oldPassword) && newPassword.equals(conformPassword)){
                customer.setPassword(conformPassword);
                customerRepository.save(customer);

            }
            else {
                throw new InvalidUserNameAndPasswordException("Invalid User Name and Password");

            }

        }else {
            throw new CustomerNotFoundException("Invalid customer Id !!");
        }





    }

    @Override
    public void addMoneyInWallet(int walletId, double amount, int accountNo) throws BankAccountNotFoundException, WalletIdNotFoundException, InsufficientBalanceException {
        if (bankAccountRepository.existsById(accountNo)) {

            if (walletRepository.existsById(walletId)) {

                Wallet wallet = walletRepository.findById(walletId).get();
                BankAccount bankAccount = bankAccountRepository.findById(accountNo).get();

                if (bankAccount.getBalance()<amount){
                    throw new InsufficientBalanceException("Insufficient Bank Amount");
                }else {


                    bankAccount.setBalance(bankAccount.getBalance() - amount);
                    wallet.setBalance(wallet.getBalance() + amount);
                    walletRepository.save(wallet);
                    bankAccountRepository.save(bankAccount);

                    Transaction transaction = new Transaction();
                    Random random = new Random();
                    int transId = random.nextInt(100000);
                    transaction.setTransactionId(transId);
                    transaction.setTransactionDate(LocalDate.now());
                    transaction.setTransactionType("credit");
                    transaction.setAmount(amount);
                    transaction.setDescription("Amount credited from bank to  wallet");
                    transaction.setWallet(wallet);
                    System.out.println(transaction);
                    transactionRepository.save(transaction);
                }
            }else {
                throw new WalletIdNotFoundException("Invalid Wallet Id ");
            }


        }
        else {
            throw new BankAccountNotFoundException("Invalid Bank Account !!");
        }
    }

    @Override
    public void fundTransfer(int userWalletId,String benMobileNo, double amount) throws BenefecieryNotFoundException, InsufficientBalanceException, WalletIdNotFoundException {

        BeneficieryDetails beneficieryDetails=beneficieryDetailsRepository.findBymobileNo(benMobileNo);
        if ( beneficieryDetails!=null){
            if (walletRepository.existsById(userWalletId)) {
                Wallet userWallet = walletRepository.findById(userWalletId).get();

                 if (userWallet.getBalance() < amount) {
                     throw new InsufficientBalanceException("Insufficient amount in wallet Please add Amount");
                 }else {


                     // Send amount from wallet to Beneficiary
//                     BeneficieryDetails beneficieryDetails = beneficieryDetailsRepository.findBymobileNo(benMobileNo);
                     beneficieryDetails.getWallet().setBalance(beneficieryDetails.getWallet().getBalance() + amount);
                     beneficieryDetailsRepository.save(beneficieryDetails);
                     userWallet.setBalance(userWallet.getBalance() - amount);

                     // Transaction Generated After Amount Transfer
                     Transaction transaction = new Transaction();
                     Random random = new Random();
                     int transId = random.nextInt(100000);
                     transaction.setTransactionId(transId);
                     transaction.setTransactionDate(LocalDate.now());
                     transaction.setTransactionType("Debit");
                     transaction.setAmount(amount);
                     transaction.setDescription("Amount transfer from Wallet to Beneficiary");
                     transaction.setWallet(userWallet);
                     transactionRepository.save(transaction);
                     transactionRepository.save(transaction);
                     System.out.println("Payment Success");

                 }
            }else {
                throw new WalletIdNotFoundException("Invalid Wallet Id");
            }

        }
        else{
            throw new BenefecieryNotFoundException("Invalid Beneficiary Detail");
        }


    }

    @Override
    public void depositAmount(int walletId,double amount, int accountNo) throws WalletIdNotFoundException, BankAccountNotFoundException, InsufficientBalanceException {


        if (walletRepository.existsById(walletId)) {
            Wallet wallet=walletRepository.findById(walletId).get();

            if (bankAccountRepository.existsById(accountNo)) {


                if (wallet.getBalance() < amount) {
                    throw new InsufficientBalanceException("Insufficient amount in wallet Please add Amount");
                } else {
                    BankAccount bankAccount = bankAccountRepository.findById(accountNo).get();
                    bankAccount.setBalance(bankAccount.getBalance() + amount);
                    wallet.setBalance(wallet.getBalance() - amount);
                    walletRepository.save(wallet);
                    bankAccountRepository.save(bankAccount);

                    Transaction transaction = new Transaction();
                    Random random = new Random();
                    int transId = random.nextInt(100000);
                    transaction.setTransactionId(transId);
                    transaction.setTransactionDate(LocalDate.now());
                    transaction.setTransactionType("debit");
                    transaction.setAmount(amount);
                    transaction.setDescription("Amount credited from Wallet to  bank");
                    transaction.setWallet(wallet);
                    transactionRepository.save(transaction);
                }
            }else {
                throw new BankAccountNotFoundException("Invalid Bank Account ");
            }


        }else{
                throw new WalletIdNotFoundException("Invalid Wallet Id");

        }

    }
    @Override
    public void billPayment(int walletId, double amount) throws WalletIdNotFoundException, InsufficientBalanceException {

        if (walletRepository.existsById(walletId)) {


            Wallet wallet = walletRepository.findById(walletId).get();

            if(wallet.getBalance()<amount){
                throw new InsufficientBalanceException("Insufficient amount in wallet Please add Amount");
            }
            else {
                wallet.setBalance(wallet.getBalance() - amount);
                walletRepository.save(wallet);

                Random random = new Random();
                int Id = random.nextInt(100000);
                Transaction transaction = new Transaction();
                transaction.setTransactionId(Id);
                transaction.setTransactionDate(LocalDate.now());
                transaction.setTransactionType("debit");
                transaction.setAmount(amount);
                transaction.setDescription("Online transaction amount from wallet !! ");
                transaction.setWallet(wallet);
                transactionRepository.save(transaction);

                BillPayment billPayment = new BillPayment();
                billPayment.setBillId(Id);
                billPayment.setPaymentDate(LocalDate.now());
                billPayment.setWallet(wallet);
                billPayment.setAmount(amount);
                billPayment.setBillType("online Payment");
                billPaymentRepository.save(billPayment);
            }
        }
        else {
            throw new WalletIdNotFoundException("Invalid Wallet Id !!!");
        }
    }

}
