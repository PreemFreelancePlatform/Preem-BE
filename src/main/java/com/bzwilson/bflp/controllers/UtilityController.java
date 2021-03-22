package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.exceptions.ResourceNotFoundException;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.View;
import com.bzwilson.bflp.repositories.CustomerRepo;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import com.bzwilson.bflp.services.Security.EmailSenderService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class UtilityController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private FreelancerRepo freelancerRepo;

    @Autowired
    private EmailSenderService emailSenderService;

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



    @PostMapping(value = "recover/{email}")
    public ResponseEntity<?> recovery(@PathVariable String email, @RequestParam(value = "answer1") String answer1, @RequestParam(value = "answer2") String answer2) {

        Freelancer freelancer = freelancerRepo.findByEmail(email);

        if(freelancer == null) {
            Customer customer = customerRepo.findByEmail(email);

            if (customer.getSecurity1().equals(answer1) & customer.getSecurity2().equals(answer2)){
                String newcuspassword = new String(answer1 + answer2);
                newcuspassword = newcuspassword.replaceAll("\\s", "");
                customer.setPassword(newcuspassword);
                customerRepo.save(customer);
                emailSenderService.sendSimpleEmail(email, "New password is " + newcuspassword, "Recovery");
                return new ResponseEntity<>("CORRECT",HttpStatus.OK);
            } else {
                return new ResponseEntity<>("WRONG",HttpStatus.OK);
            }

        }

        if (freelancer.getSecurity1().equals(answer1) & freelancer.getSecurity2().equals(answer2)){
            String newfreepassword = new String(answer1 + answer2);
            newfreepassword = newfreepassword.replaceAll("\\s", "");
            freelancer.setPassword(newfreepassword);
            freelancerRepo.save(freelancer);
            emailSenderService.sendSimpleEmail(email,

                    "Hello! your new temporary password is " + newfreepassword + ", please log in and change your password immediately.",

                    "Recovery");
            return new ResponseEntity<>("CORRECT",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("WRONG",HttpStatus.OK);
        }


//        if (freelancer == null) {
//            Customer customer = customerRepo.findByEmail(email);
//        }



    }


    @JsonView(View.Recovery.class)
    @GetMapping(value = "/getquestions/{email}")
    public ResponseEntity<?> getQuestions(@PathVariable
                                                      String email) {
        Customer customer = customerRepo.findByEmail(email);
        if (customer == null) {
            Freelancer freelancer = freelancerRepo.findByEmail(email);
            if (freelancer == null){
                throw new ResourceNotFoundException("no user with that email");
            }
            return new ResponseEntity<>(freelancer,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(customer,
                    HttpStatus.OK);
        }
    }
}

