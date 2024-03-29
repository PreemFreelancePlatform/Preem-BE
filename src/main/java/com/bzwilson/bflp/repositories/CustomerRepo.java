package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

//    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
