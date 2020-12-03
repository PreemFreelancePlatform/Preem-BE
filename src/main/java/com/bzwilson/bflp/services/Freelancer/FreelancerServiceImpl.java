package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.exceptions.RestrictionException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "freelancerservice")
public class FreelancerServiceImpl implements FreelancerService {


    @Autowired
    private FreelancerRepo freerepo;

    @Autowired
    private CustomerPostRepo postRepo;

    
    @Override
    public Freelancer findByUsername(String username) {
        Freelancer uu = freerepo.findByUsername(username.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("User name " + username + " not found!");
        }
        return uu;
    }


    @Override
    public List<Freelancer> findAll() {
        List<Freelancer> list = new ArrayList<>();
        freerepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Freelancer FindFreelancerById(long id)
            throws
            ResourceNotFoundException {
        return freerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer " + id + " not found!"));
    }


    @Override
    public void delete(long id) {
        freerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer " + id + " not found!"));
        freerepo.deleteById(id);
    }

    @Transactional
    @Override
    public Freelancer save(Freelancer freelancer) {

        // making new customerpost object
        Freelancer newfreelancer = new Freelancer();

        // if we get an id back then set it to new customer
        if (freelancer.getId() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newfreelancer.setId(freelancer.getId());
        }


        // delete the roles for the old user we are replacing
        //not using roles rn
//            for (UserRole ur: User.getUserroles() {
//                 oldCustomer.getCustomerposts()) {
//                deleteUserRole(ur.getUser()
//                                .getUserid(),
//                        ur.getRole()
//                                .getRoleid());
//            }
//        }


        newfreelancer.setEmail(freelancer.getEmail());

        newfreelancer.setUsername(freelancer.getUsername());


        newfreelancer.setPasswordnoEncrypt(freelancer.getPassword());

        newfreelancer.setLOCKED_role(freelancer.getLOCKED_role());

        newfreelancer.setTutorial(freelancer.getTutorial());

        newfreelancer.setSetup(freelancer.getSetup());

        newfreelancer.setPicByte(freelancer.getPicByte());


        newfreelancer.getCustomerposts()
                .clear();
        for (CustomerPosts cp : freelancer.getCustomerposts()) {
            newfreelancer.getCustomerposts()
                    .add(new CustomerPosts(cp.getTask(), cp.getDescription(), cp.getField(), cp.getSpecialization(), cp.getBudget(), cp.getDuedate(), cp.getPostdate(), cp.getCustomer()));
        }

        return freerepo.save(newfreelancer);
    }

    @Transactional
    @Override
    public Freelancer update(
            Freelancer freelancer,
            long id) {

        Freelancer currentfreelancer = FindFreelancerById(id);

        // WILL I NEED THIS LATER??
//        if (helper.isAuthorizedToMakeChange(currentUser.getUsername())) {

        if (freelancer.getEmail() != null) {
            currentfreelancer.setEmail(freelancer.getEmail());
        }

        if (freelancer.getUsername() != null) {
            currentfreelancer.setUsername(freelancer.getUsername());
        }


        if (freelancer.getPassword() != null) {
            currentfreelancer.setPasswordnoEncrypt(freelancer.getPassword());
        }

        if (freelancer.getLOCKED_role() != null) {
            throw new RestrictionException("you cannot change your role");
        }

        if (freelancer.getPicByte() != null) {
            currentfreelancer.setPicByte(freelancer.getPicByte());
        }



        if (freelancer.getCustomerposts().size() > 0) {
            for (CustomerPosts cp : freelancer.getCustomerposts()) {
                currentfreelancer.getCustomerposts()
                        .add(new CustomerPosts(cp.getTask(), cp.getDescription(), cp.getField(), cp.getSpecialization(), cp.getBudget(), cp.getDuedate(), cp.getPostdate(), cp.getCustomer()));
            }

        }

        return freerepo.save(currentfreelancer);
    }


    @Override
    public Freelancer didTutorial(long id) {
        Freelancer currentFreelancer = FindFreelancerById(id);
        currentFreelancer.setTutorial(true);
        return freerepo.save(currentFreelancer);
    }

    @Override
    public Freelancer isSetup(long id) {
        Freelancer currentFreelancer = FindFreelancerById(id);
        currentFreelancer.setSetup(true);
        return freerepo.save(currentFreelancer);
    }

}
//


