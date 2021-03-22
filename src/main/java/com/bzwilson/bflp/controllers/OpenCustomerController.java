package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


// MAKE DEFAULT ROLE
@RestController
public class OpenCustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HelperFunctions helper;

    @PostMapping(value = "/createnewcustomer",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addCustomer(
            HttpServletRequest httpServletRequest,
            @RequestBody
                    Customer newmincustomer,
            @RequestParam("imageFile") MultipartFile file)
            throws
            URISyntaxException, IOException {
        // Create the user

        // if the username we are trying to make exists elsewhere throw exception
//        if (helper.customerUserNameisAvailable(newmincustomer.getUsername())) {

            Customer tempcustomer = new Customer();

            tempcustomer.setFirstname(newmincustomer.getFirstname());
            tempcustomer.setLastname(newmincustomer.getLastname());
            tempcustomer.setEmail(newmincustomer.getEmail());
            tempcustomer.setPassword(newmincustomer.getPassword());
            tempcustomer.setLOCKED_role("customer");
            tempcustomer.setTutorial(false);
            tempcustomer.setVerified(false);
            tempcustomer.setSecurity1(newmincustomer.getSecurity1());
            tempcustomer.setSecurity1(newmincustomer.getSecurity2());
            tempcustomer.setPicByte(file.getBytes());




        // add the default role of user
//        List<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newuser,
//                roleService.findByName("user")));
//        newuser.setRoles(newRoles);

            tempcustomer = customerService.save(tempcustomer);

            // set the location header for the newly created resource
            // The location comes from a different controller!
            HttpHeaders responseHeaders = new HttpHeaders();
            URI newCustomerURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "freelancer/freelancer/{id}")
                    .buildAndExpand(tempcustomer.getCustomerid())
                    .toUri();
            responseHeaders.setLocation(newCustomerURI);

            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.CREATED);
        }
//        else {
//
//            throw new ResourceFoundException("this username has been taken");
//          }
//        }


}



