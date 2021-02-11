package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerPostService {
    Page<CustomerPosts> findAll(Pageable pageable);

    Page<CustomerPosts> findAllByFieldAndSpecializationInAndBudgetBetween(String field, List<String> specialization, Double min, Double max, Pageable pageable);

    Page<CustomerPosts> findAllByFieldAndBudgetBetween(String field, Double min, Double max, Pageable pageable);

    CustomerPosts findByCustomerPostId(long id);

    void delete(long id);

    CustomerPosts save(CustomerPosts customerpost);

    CustomerPosts update(
            CustomerPosts customerpost,
            long id);

    CustomerPosts apply(long pid, long fid);

}
