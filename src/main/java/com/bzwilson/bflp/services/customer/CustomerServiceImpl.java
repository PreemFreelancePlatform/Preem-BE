package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
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

    @Autowired
    private HelperFunctions helper;


    @Override
    public Customer findByUsername(String username) {
        Customer uu = customerrepo.findByUsername(username.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("User name " + username + " not found!");
        }
        return uu;
    }


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


        newCustomer.setUsername(customer.getUsername());

        newCustomer.setCustomeremail(customer.getCustomeremail());

        newCustomer.setPasswordNoEncrypt(customer.getPassword());

        newCustomer.setLOCKED_role(customer.getLOCKED_role());


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
        if (helper.isAuthorizedToMakeChange(currentCustomer.getUsername())) {

            if (customer.getUsername() != null) {
                currentCustomer.setUsername(customer.getUsername());
            }

            if (customer.getCustomeremail() != null) {
                currentCustomer.setCustomeremail(customer.getCustomeremail());
            }

            // MAKESURE TO ENCRTYPT
            if (customer.getPassword() != null) {
                currentCustomer.setPasswordNoEncrypt(customer.getPassword());
            }

            if (customer.getLOCKED_role() != null) {
                currentCustomer.setLOCKED_role(customer.getLOCKED_role());
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
        } else {

            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException(customer.getUsername() + " is not authorized to make change");
            
        }

    }

}

// IF NOT AUTH THROW EXCEPTION NOT AUTHORIZED