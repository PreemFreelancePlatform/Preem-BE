package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerPostRepo extends CrudRepository<CustomerPosts, Long> {

    List<CustomerPosts> findAllByField(String field);
}
