package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findCustomerById(long id);

    void delete(long id);

    Customer save(Customer customer);

    Customer update(
            Customer customer,
            long id);
}