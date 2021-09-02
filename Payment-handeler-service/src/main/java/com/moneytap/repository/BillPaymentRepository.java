package com.moneytap.repository;

import com.moneytap.model.BillPayment;
import org.springframework.data.repository.CrudRepository;

public interface BillPaymentRepository extends CrudRepository<BillPayment,Integer> {
}
