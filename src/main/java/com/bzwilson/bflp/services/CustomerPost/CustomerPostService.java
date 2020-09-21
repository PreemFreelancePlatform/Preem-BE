package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.models.CustomerPost;

import java.util.List;

public interface CustomerPostService {
    List<CustomerPost> findAll();

    CustomerPost findCustomerPostById(long id);

    CustomerPost findCustomerPostByName(String name);

    void delete(long id);

    CustomerPost save(CustomerPost Customerpost);

    CustomerPost update(
            CustomerPost Customerpost,
            long id);
}
