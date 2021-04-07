package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerPostService {
    Page<CustomerPosts> findAll(int page, int size);

    Page<CustomerPosts> findAllByCategoryInAndTagsInAndBudgetBetween(List<String> category, List<String> tags, Double min, Double max, int page, Pageable pageable);
    Page<CustomerPosts> findAllByCategoryInAndBudgetBetween(List<String> category, Double min, Double max, int page, Pageable pageable);

    CustomerPosts findByCustomerPostId(long id);

    void delete(long id);

    CustomerPosts removeFreelancerFromPost(long pid, long fid);

    CustomerPosts save(CustomerPosts customerpost);

    CustomerPosts update(
            CustomerPosts customerpost,
            long id);

    CustomerPosts apply(long pid, long fid);

}
