package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.models.CustomerPosts;

import java.util.List;

public interface CustomerPostService {
    List<CustomerPosts> findAll();

    CustomerPosts findByCustomerPostId(long id);

    void delete(long id);

    CustomerPosts save(CustomerPosts customerpost);

    CustomerPosts update(
            CustomerPosts customerpost,
            long id);

}
