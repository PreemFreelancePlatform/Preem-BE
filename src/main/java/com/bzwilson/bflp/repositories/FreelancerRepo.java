package com.bzwilson.bflp.repositories;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreelancerRepo extends CrudRepository<Freelancer, Long> {


//    List<Freelancer> findAllByCategoryOrTagsIn(String category, List<String> tags);
//    Freelancer findByUsername(String username);
    Freelancer findByEmail(String email);



}
