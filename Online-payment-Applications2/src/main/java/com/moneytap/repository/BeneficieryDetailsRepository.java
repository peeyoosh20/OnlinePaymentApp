package com.moneytap.repository;

import com.moneytap.model.BeneficieryDetails;
import org.springframework.data.repository.CrudRepository;

public interface BeneficieryDetailsRepository extends CrudRepository<BeneficieryDetails,Integer> {
    BeneficieryDetails findBymobileNo(String mobileNo);
}
