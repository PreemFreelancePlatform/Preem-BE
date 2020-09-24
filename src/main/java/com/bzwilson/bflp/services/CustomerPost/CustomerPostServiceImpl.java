package com.bzwilson.bflp.services.CustomerPost;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.repositories.CustomerPostRepo;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerPostService")
public class CustomerPostServiceImpl implements CustomerPostService {

    @Autowired
    private CustomerPostRepo customerpostrepo;

    @Autowired
    private CustomerService customerService;

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




//        newCustomer.getCustomerposts()
//                .clear();
//        for (CustomerPosts cp : customer.getCustomerposts()) {
//            newCustomer.getCustomerposts()
//                    .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), newCustomer));
//        }

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

        return customerpostrepo.save(currentcustomerposts);
    }
}
