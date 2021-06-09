package com.bzwilson.bflp.services.Contracts;

import com.bzwilson.bflp.models.Contract;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;

import java.util.List;

public interface ContractService {

    List<Contract> findAll();

    Contract findContractById(long id);

    void delete(long id);

    Contract createnew(Contract contract);

    Contract update(
            Contract contract,
            long id);

    List<Contract> findAllByCustomer_Email(String email);
    List<Contract> findAllByFreelancer_Email(String email);




}
