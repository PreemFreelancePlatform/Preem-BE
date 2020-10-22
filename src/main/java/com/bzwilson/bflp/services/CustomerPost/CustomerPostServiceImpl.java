package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerPostService")
public class CustomerPostServiceImpl implements CustomerPostService {

    @Autowired
    private CustomerPostRepo customerpostrepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FreelancerRepo freerepo;

    @Override
    public List<CustomerPosts> findAll() {
        List<CustomerPosts> list = new ArrayList<>();
        customerpostrepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public CustomerPosts findByCustomerPostId(long id)
            throws
            ResourceNotFoundException {
        return customerpostrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer post " + id + " not found!"));
    }


    @Override
    public void delete(long id) {
        customerpostrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer post " + id + " not found!"));
        customerpostrepo.deleteById(id);
    }

    @Transactional
    @Override
    public CustomerPosts save(CustomerPosts customerposts) {

        // making new customerpost object
        CustomerPosts newCustomerPosts = new CustomerPosts();

        // if we get an id back then set it to new customer
        if (customerposts.getPostid() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newCustomerPosts.setPostid(customerposts.getPostid());
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


        newCustomerPosts.setName(customerposts.getName());

        newCustomerPosts.setDescription(customerposts.getDescription());

        newCustomerPosts.setTech(customerposts.getTech());

        newCustomerPosts.setCustomer(customerposts.getCustomer());


        // REMEMBER TO ENCRYPT PASSWORD
//            customer.setPasswordNoEncrypt(customer.getPassword());


        newCustomerPosts.getFreelancers()
                .clear();
        for (Freelancer fl : customerposts.getFreelancers()) {
            newCustomerPosts.getFreelancers()
                    .add(new Freelancer(fl.getEmail(), fl.getUsername(), fl.getPassword(), fl.getLOCKED_role(), fl.getPicByte(), fl.getTutorial(), fl.getSetup()));
        }

        return customerpostrepo.save(newCustomerPosts);
    }

    @Transactional
    @Override
    public CustomerPosts update(
            CustomerPosts customerpost,
            long id) {

        CustomerPosts currentcustomerposts = findByCustomerPostId(id);

        // WILL I NEED THIS LATER??
//        if (helper.isAuthorizedToMakeChange(currentUser.getUsername())) {

        if (customerpost.getName() != null) {
            currentcustomerposts.setName(customerpost.getName());
        }

        if (customerpost.getDescription() != null) {
            currentcustomerposts.setDescription(customerpost.getDescription());
        }

        if (customerpost.getTech() != null) {
            currentcustomerposts.setTech(customerpost.getTech());
        }

        if (customerpost.getFreelancers()
                .size() > 0) {
            currentcustomerposts.getFreelancers().clear();
            for (Freelancer fl : customerpost.getFreelancers()) {
                currentcustomerposts.getFreelancers()
                        .add(new Freelancer(fl.getEmail(), fl.getUsername(), fl.getPassword(), fl.getLOCKED_role(), fl.getPicByte(), fl.getTutorial(), fl.getSetup()));
            }
        }

        return customerpostrepo.save(currentcustomerposts);
    }


    @Override
    public CustomerPosts apply(long fid, long pid) {

        CustomerPosts cp = customerpostrepo.findById(pid).orElseThrow(EntityNotFoundException::new);
        Freelancer fl = freerepo.findById(fid).orElseThrow(EntityNotFoundException::new);

        cp.getFreelancers().add(fl);

        return customerpostrepo.save(cp);
    }
}
