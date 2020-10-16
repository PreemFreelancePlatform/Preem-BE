package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.exceptions.ResourceFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerMin;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;


// MAKE DEFAULT ROLE
@RestController
public class OpenCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private HelperFunctions helper;


    @PostMapping(value = "/createnewcustomer",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addCustomer(
            HttpServletRequest httpServletRequest,
            @RequestBody
                    CustomerMin newmincustomer)
            throws
            URISyntaxException {
        // Create the user

        // if the username we are trying to make exists elsewhere throw exception
        if (helper.customerUserNameisAvailable(newmincustomer.getUsername())) {

            Customer newcustomer = new Customer();

            newcustomer.setUsername(newmincustomer.getUsername());
            newcustomer.setPassword(newmincustomer.getPassword());
            newcustomer.setCustomeremail(newmincustomer.getCustomeremail());
            newcustomer.setLOCKED_role("customer");
            newcustomer.setTutorial(false);
            newcustomer.setSetup(false);

            // add the default role of user
//        List<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newuser,
//                roleService.findByName("user")));
//        newuser.setRoles(newRoles);

            newcustomer = customerService.save(newcustomer);

            // set the location header for the newly created resource
            // The location comes from a different controller!
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newCustomerURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/customer/{customerid}")
                    .buildAndExpand(newcustomer.getId())
                    .toUri();
            responseHeaders.setLocation(newCustomerURI);

            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.CREATED);
        } else {

            throw new ResourceFoundException("this username has been taken");
        }
    }

}



