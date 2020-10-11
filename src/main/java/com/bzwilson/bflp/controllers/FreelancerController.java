package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {


    @Autowired
    private FreelancerService freelancerServices;

    @Autowired
    private CustomerPostService customerPostService;


    // ADMIN ONLY
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @GetMapping(value = "/freelancers",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<Freelancer> mylancers = freelancerServices.findAll();

        return new ResponseEntity<>(mylancers,
                HttpStatus.OK);
    }

    // FREELANCER AUTHORIZED IF I WANT TO GET MYSELF USE CURRENTLY AUTHENTICATED METHOD
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_FREELANCER')")
    @GetMapping(value = "/freelancer/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> FindFreelancerById(
            @PathVariable
                    Long id) {
        Freelancer u = freelancerServices.FindFreelancerById(id);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // FREELANCER AUTHORIZED  CONSIDER USING AUTHENTICATED USER FOR THIS LATER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PostMapping(value = "/freelancer/{freelancerid}/post/{postid}")
    public ResponseEntity<?> AddFreelancertopost(
            @PathVariable
                    long freelancerid,
            @PathVariable
                    long postid) {


        // do this for adding

        customerPostService.apply(freelancerid, postid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // FREELANCER AUTHORIZED
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PatchMapping(value = "/freelancer/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updatePost(
            @RequestBody
                    Freelancer freelancer,
            @PathVariable
                    long id) {
        freelancerServices.update(freelancer,
                id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ADMIN ONLY
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @PutMapping(value = "/freelancer/{freelancerid}")
//    public ResponseEntity<?> savePost(
//            @RequestBody Freelancer freelancer,
//            @PathVariable long freelancerid) {
//        freelancer.setFreelancerid(freelancerid);
//        freelancerServices.save(freelancer);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // ADMIN ONLY
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "freelancer/{freelancerid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long freelancerid) {
        freelancerServices.delete(freelancerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PatchMapping(value = "/{fid}/tutorial")
    public ResponseEntity<?> setTut(
            @PathVariable
                    long fid) {
        freelancerServices.didTutorial(fid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PatchMapping(value = "/{fid}/setup")
    public ResponseEntity<?> setSetup(
            @PathVariable
                    long fid) {
        freelancerServices.isSetup(fid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
