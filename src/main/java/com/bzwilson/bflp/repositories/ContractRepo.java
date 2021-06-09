package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.Contract;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractRepo extends CrudRepository<Contract, Long> {
    List<Contract> findAllByCustomer_Email(String email);
    List<Contract> findAllByFreelancer_Email(String email);


}
