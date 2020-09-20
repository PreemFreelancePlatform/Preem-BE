package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.UserProperty;
import org.springframework.data.repository.CrudRepository;

public interface UserPropertyRepo extends CrudRepository<UserProperty, Long> {

}
