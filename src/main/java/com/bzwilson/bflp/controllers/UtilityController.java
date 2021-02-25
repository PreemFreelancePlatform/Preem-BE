package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private FreelancerRepo freelancerRepo;

    @ApiOperation(value = "returns the currently authenticated user",
            response = Customer.class)
    @GetMapping(value = "/getmyinfo",
            produces = {"application/json"})

    public ResponseEntity<?> getCurrentUserInfo(Authentication authentication) {
        Customer customer = customerRepo.findByEmail(authentication.getName());
        if (customer == null) {
            Freelancer freelancer = freelancerRepo.findByEmail(authentication.getName());
            return new ResponseEntity<>(freelancer,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(customer,
                    HttpStatus.OK);
        }
    }
}
