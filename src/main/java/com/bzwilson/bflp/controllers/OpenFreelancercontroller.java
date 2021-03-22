package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.MinUser;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
@RestController
public class OpenFreelancercontroller {

    @Autowired
    private FreelancerService freelancerservice;

    @PostMapping(value = "/createnewfreelancer",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addSelf(
            HttpServletRequest httpServletRequest,
            @Valid
            @RequestBody
                MinUser minUser)
            throws
            URISyntaxException {

//        if (helper.freelancerUserNameisAvailable(newminFreelancer.getUsername())) {

            Freelancer newfreelancer = new Freelancer();

            newfreelancer.setFirstname(minUser.getFirstname());
            newfreelancer.setLastname(minUser.getLastname());
            newfreelancer.setEmail(minUser.getEmail());
            newfreelancer.setPassword(minUser.getPassword());
            newfreelancer.setLOCKED_role("freelancer");
            newfreelancer.setSetup(false);

            // add the default role of user
//        List<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newuser,
//                roleService.findByName("user")));
//        newuser.setRoles(newRoles);

            newfreelancer = freelancerservice.save(newfreelancer);


        return new ResponseEntity<>(
                HttpStatus.CREATED);
    }

        }
//        else {
//            throw new ResourceFoundException("The Username " + newminFreelancer.getUsername() + " has been taken");
//        }
//    }

