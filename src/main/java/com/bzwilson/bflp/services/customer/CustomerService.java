package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CustomerService {

    Customer findByEmail(String email);

    List<Customer> findAll();

    Customer findCustomerById(long id);

    void delete(long id);

    Customer save(Customer customer);

    Customer update(
            Customer customer,
            long id);

    Customer didTutorial(long id);

    Customer isSetup(long id);


}
