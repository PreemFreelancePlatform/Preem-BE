package com.bzwilson.bflp.services.customer;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.exceptions.RestrictionException;
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
        if (customer.getId() != 0) {
//            Customer oldCustomer = customerrepo.findById(customer.getCustomerid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id " + customer.getCustomerid() + " not found!"));
            newCustomer.setId(customer.getId());
        }


        newCustomer.setUsername(customer.getUsername());

        newCustomer.setCustomeremail(customer.getCustomeremail());

        newCustomer.setPasswordNoEncrypt(customer.getPassword());

        newCustomer.setLOCKED_role(customer.getLOCKED_role());

        newCustomer.setTutorial(customer.getTutorial());

        newCustomer.setSetup(customer.getSetup());

        newCustomer.setPicByte(customer.getPicByte());

        newCustomer.getCustomerposts()
                .clear();
        for (CustomerPosts cp : customer.getCustomerposts()) {
            newCustomer.getCustomerposts()
                    .add(new CustomerPosts(cp.getTask(), cp.getDescription(), cp.getField(), cp.getSpecialization(), cp.getBudget(), cp.getDuedate(), newCustomer));
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
                throw new RestrictionException("you cannot change your role");
            }

            if (customer.getTutorial() != null) {
                currentCustomer.setTutorial(customer.getTutorial());
            }

            if (customer.getSetup() != null) {
                currentCustomer.setSetup(customer.getSetup());
            }

            if (customer.getPicByte() != null) {
                currentCustomer.setPicByte(customer.getPicByte());
            }
            // test to clear
            if (customer.getCustomerposts()
                    .size() > 0) {
                currentCustomer.getCustomerposts().clear();
                for (CustomerPosts cp : customer.getCustomerposts()) {
                    currentCustomer.getCustomerposts()
                            .add(new CustomerPosts(cp.getTask(), cp.getDescription(), cp.getField(), cp.getSpecialization(), cp.getBudget(), cp.getDuedate(), currentCustomer));
                }
            }


            return customerrepo.save(currentCustomer);
        } else {

            throw new ResourceNotFoundException(customer.getUsername() + " is not authorized to make change");

        }


    }

    @Override
    public Customer didTutorial(long id) {
        Customer currentCustomer = findCustomerById(id);
        currentCustomer.setTutorial(true);
        return customerrepo.save(currentCustomer);
    }

    @Override
    public Customer isSetup(long id) {
        Customer currentCustomer = findCustomerById(id);
        currentCustomer.setSetup(true);
        return customerrepo.save(currentCustomer);
    }

}

