package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPost;
import org.springframework.data.repository.CrudRepository;

public interface CustomerPostRepo extends CrudRepository<CustomerPost, Long> {
}
