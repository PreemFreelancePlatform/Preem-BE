package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Override
//    public List<CustomerPosts> findAll(Pageable pageable) {
//        List<CustomerPosts> list = new ArrayList<>();
//        customerpostrepo.findAll()
//                .iterator()
//                .forEachRemaining(list::add);
//        return list;
//    }

    @Override
    public Page<CustomerPosts> findAll(Pageable pageable) {
        return customerpostrepo.findAll(pageable);
    }

    @Override
    public Page<CustomerPosts> findAllByFieldAndSpecializationInAndBudgetBetween(String field, List<String> specialization, Double min, Double max, Pageable pageable) {
      return customerpostrepo.findAllByFieldAndSpecializationInAndBudgetBetween(field, specialization, min, max, pageable);
    }

    @Override
    public Page<CustomerPosts> findAllByFieldAndBudgetBetween(String field, Double min, Double max, Pageable pageable) {
        return customerpostrepo.findAllByFieldAndBudgetBetween(field, min, max, pageable);
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


        newCustomerPosts.setTask(customerposts.getTask());

        newCustomerPosts.setDescription(customerposts.getDescription());

        newCustomerPosts.setField(customerposts.getField());

        newCustomerPosts.setSpecialization(customerposts.getSpecialization());

        newCustomerPosts.setBudget(customerposts.getBudget());

        newCustomerPosts.setCustomer(customerposts.getCustomer());



        // REMEMBER TO ENCRYPT PASSWORD
//            customer.setPasswordNoEncrypt(customer.getPassword());


        newCustomerPosts.getFreelancers()
                .clear();
        for (Freelancer fl : customerposts.getFreelancers()) {
            newCustomerPosts.getFreelancers()
                    .add(new Freelancer(fl.getEmail(), fl.getUsername(), fl.getCategory(), fl.getPassword(), fl.getLOCKED_role(),  fl.getTutorial(), fl.getSetup(), fl.getTags(),  fl.getPicByte()));
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

        if (customerpost.getTask() != null) {
            currentcustomerposts.setTask(customerpost.getTask());
        }

        if (customerpost.getDescription() != null) {
            currentcustomerposts.setDescription(customerpost.getDescription());
        }

        if (customerpost.getField() != null) {
            currentcustomerposts.setField(customerpost.getField());
        }

        if (customerpost.getField() != null) {
            currentcustomerposts.setField(customerpost.getField());
        }

        if (customerpost.getSpecialization() != null) {
            currentcustomerposts.setSpecialization(customerpost.getSpecialization());
        }

        if (customerpost.getFreelancers()
                .size() > 0) {
            currentcustomerposts.getFreelancers().clear();
            for (Freelancer fl : customerpost.getFreelancers()) {
                currentcustomerposts.getFreelancers()
                        .add(new Freelancer(fl.getEmail(), fl.getUsername(), fl.getCategory(), fl.getPassword(), fl.getLOCKED_role(), fl.getTutorial(), fl.getSetup(), fl.getTags(), fl.getPicByte()));
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
