package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.FreelancerMin;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
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

@RestController
public class OpenFreelancercontroller {

    @Autowired
    private FreelancerService freelancerservice;


    @PostMapping(value = "/createnewfreelancer",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addFreelancer(
            HttpServletRequest httpServletRequest,
            @RequestBody
                    FreelancerMin newminFreelancer)
            throws
            URISyntaxException {
        // Create the user
        Freelancer newfreelancer = new Freelancer();

        newfreelancer.setEmail(newminFreelancer.getEmail());
        newfreelancer.setUsername(newminFreelancer.getUsername());
        newfreelancer.setFirstname(newminFreelancer.getFirstname());
        newfreelancer.setPassword(newminFreelancer.getPassword());


        // add the default role of user
//        List<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newuser,
//                roleService.findByName("user")));
//        newuser.setRoles(newRoles);

        newfreelancer = freelancerservice.save(newfreelancer);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newFreelancerURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/freelancer/{freelancerid}")
                .buildAndExpand(newfreelancer.getFreelancerid())
                .toUri();
        responseHeaders.setLocation(newFreelancerURI);

        return new ResponseEntity<>(
                responseHeaders,
                HttpStatus.CREATED);
    }
}
