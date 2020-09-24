package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;

import java.util.List;

public interface FreelancerServices {
    List<Freelancer> findAll();

    Freelancer FindFreelancerById(long id);

    void delete(long id);

    Freelancer save(Freelancer freelancer);

    Freelancer update(
            Freelancer freelancer,
            long id);
}