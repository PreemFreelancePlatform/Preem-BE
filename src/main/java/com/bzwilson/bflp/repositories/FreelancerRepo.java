package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.Freelancer;
import org.springframework.data.repository.CrudRepository;

public interface FreelancerRepo extends CrudRepository<Freelancer, Long> {

    Freelancer findByUsername(String username);

}
