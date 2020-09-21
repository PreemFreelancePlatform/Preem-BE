package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import org.springframework.data.repository.CrudRepository;

public interface CustomerPostRepo extends CrudRepository<CustomerPosts, Long> {
}
