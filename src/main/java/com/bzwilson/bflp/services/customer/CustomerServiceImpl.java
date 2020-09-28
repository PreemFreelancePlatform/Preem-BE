package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerrepo;


    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        customerrepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Customer findCustomerById(long id)
            throws
            ResourceNotFoundException {
        return customerrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found!"));
    }


    @Transactional
    @Override
    public void delete(long id) {
        customerrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found!"));
        customerrepo.deleteById(id);
    }


    // NOTE THAT YOU NEED TO ENCRYPT PASSWORD ON MODELS AND HERE AS WELL
    @Transactional
    @Override
    public Customer save(Customer customer) {

        // making new customer object
        Customer newCustomer = new Customer();

        // if we get an id back then set it to new customer
        if (customer.getCustomerid() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newCustomer.setCustomerid(customer.getCustomerid());
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


        newCustomer.setUsername(customer.getUsername());

        newCustomer.setCustomeremail(customer.getCustomeremail());

        // REMEMBER TO ENCRYPT PASSWORD
//            customer.setPasswordNoEncrypt(customer.getPassword());

        newCustomer.setPassword(customer.getPassword());


        newCustomer.getCustomerposts()
                .clear();
        for (CustomerPosts cp : customer.getCustomerposts()) {
            newCustomer.getCustomerposts()
                    .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), newCustomer));
        }

        return customerrepo.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(
            Customer customer,
            long id) {
        Customer currentCustomer = findCustomerById(id);

        // WILL I NEED THIS LATER??
//        if (helper.isAuthorizedToMakeChange(currentUser.getUsername())) {

        if (customer.getUsername() != null) {
            currentCustomer.setUsername(customer.getUsername());
        }

        if (customer.getCustomeremail() != null) {
            currentCustomer.setCustomeremail(customer.getCustomeremail());
        }

        // MAKESURE TO ENCRTYPT
        if (customer.getPassword() != null) {
            currentCustomer.setPassword(customer.getPassword());
        }
        // test to clear
        if (customer.getCustomerposts()
                .size() > 0) {
            currentCustomer.getCustomerposts().clear();
            for (CustomerPosts cp : customer.getCustomerposts()) {
                currentCustomer.getCustomerposts()
                        .add(new CustomerPosts(cp.getName(), cp.getDescription(), cp.getTech(), currentCustomer));
            }
        }


        return customerrepo.save(currentCustomer);
    }
}

// IF NOT AUTH THROW EXCEPTION NOT AUTHORIZED