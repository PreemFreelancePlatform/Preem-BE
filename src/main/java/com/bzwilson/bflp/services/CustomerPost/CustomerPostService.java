package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;

import java.util.List;

public interface CustomerPostService {
    List<CustomerPosts> findAll();

    List<CustomerPosts> findAllByField(String field);

    CustomerPosts findByCustomerPostId(long id);

    void delete(long id);

    CustomerPosts save(CustomerPosts customerpost);

    CustomerPosts update(
            CustomerPosts customerpost,
            long id);

    CustomerPosts apply(long pid, long fid);

}
