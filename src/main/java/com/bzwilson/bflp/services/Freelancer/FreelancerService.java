package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.models.Freelancer;

import java.util.List;

public interface FreelancerService {

    List<Freelancer> findAllByCategoryOrTagsIn(String category, List<String> tags);

    Freelancer findByUsername(String username);

    List<Freelancer> findAll();

    Freelancer FindFreelancerById(long id);

    void delete(long id);

    Freelancer save(Freelancer freelancer);

    Freelancer update(
            Freelancer freelancer,
            long id);

    Freelancer didTutorial(long id);

    Freelancer isSetup(long id);
}


