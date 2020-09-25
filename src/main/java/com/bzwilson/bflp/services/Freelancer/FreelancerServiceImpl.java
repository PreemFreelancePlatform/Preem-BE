package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Freelancer;
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
        if (freelancer.getFreelancerid() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newfreelancer.setFreelancerid(freelancer.getFreelancerid());
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

        newfreelancer.setFirstname(freelancer.getFirstname());

        newfreelancer.setLastname(freelancer.getLastname());

        newfreelancer.setDescription(freelancer.getDescription());

        newfreelancer.setSkills(freelancer.getSkills());

        newfreelancer.setRating(freelancer.getRating());

        newfreelancer.setPassword(freelancer.getPassword());

        newfreelancer.setCustomerPost(freelancer.getCustomerPost());





        // REMEMBER TO ENCRYPT PASSWORD
//            customer.setPasswordNoEncrypt(customer.getPassword());




//        newCustomer.getCustomerposts()
//                .clear();
//        for (CustomerPosts cp : customer.getCustomerposts()) {
//            newCustomer.getCustomerposts()
//                    .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), newCustomer));
//        }

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

        if (freelancer.getFirstname() != null) {
            currentfreelancer.setFirstname(freelancer.getFirstname());
        }

        if (freelancer.getLastname() != null) {
            currentfreelancer.setLastname(freelancer.getLastname());
        }

        if (freelancer.getDescription() != null) {
            currentfreelancer.setDescription(freelancer.getDescription());
        }

        if (freelancer.getSkills() != null) {
            currentfreelancer.setSkills(freelancer.getSkills());
        }

        if (freelancer.getRating() != 0) {
            currentfreelancer.setRating(freelancer.getRating());
        }

        if (freelancer.getPassword() != null) {
            currentfreelancer.setPassword(freelancer.getPassword());
        }

        return freerepo.save(currentfreelancer);
    }
}