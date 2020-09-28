package com.bzwilson.bflp.services.Freelancer;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

        newfreelancer.setUsername(freelancer.getUsername());

        newfreelancer.setFirstname(freelancer.getFirstname());

        newfreelancer.setRating(freelancer.getRating());

        newfreelancer.setPassword(freelancer.getPassword());


        // REMEMBER TO ENCRYPT PASSWORD
//            customer.setPasswordNoEncrypt(customer.getPassword());


        newfreelancer.getCustomerposts()
                .clear();
        for (CustomerPosts cp : freelancer.getCustomerposts()) {
            newfreelancer.getCustomerposts()
                    .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), cp.getCustomer()));
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

        if (freelancer.getFirstname() != null) {
            currentfreelancer.setFirstname(freelancer.getFirstname());
        }

        if (freelancer.getRating() != 0) {
            currentfreelancer.setRating(freelancer.getRating());
        }

        if (freelancer.getPassword() != null) {
            currentfreelancer.setPassword(freelancer.getPassword());
        }

        if (freelancer.getCustomerposts().size() > 0) {
            for (CustomerPosts cp : freelancer.getCustomerposts()) {
                currentfreelancer.getCustomerposts()
                        .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), cp.getCustomer()));
            }

        }

        return freerepo.save(currentfreelancer);
    }

    @Override
    public Freelancer apply(long fid, long pid) {

        Freelancer fl = freerepo.findById(fid).orElseThrow(EntityNotFoundException::new);
        CustomerPosts cp = postRepo.findById(pid).orElseThrow(EntityNotFoundException::new);

        fl.getCustomerposts().add(cp);

        return freerepo.save(fl);
    }
}
//


