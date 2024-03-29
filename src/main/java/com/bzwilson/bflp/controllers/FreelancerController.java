package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.HelperFunctions.HelperFunctions;
import com.bzwilson.bflp.exceptions.RestrictionException;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.View;
import com.bzwilson.bflp.repositories.FreelancerRepo;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity.BodyBuilder;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {


    @Autowired
    private FreelancerService freelancerServices;

    @Autowired
    private CustomerPostService customerPostService;

    @Autowired
    private FreelancerRepo freelancerRepo;

    @Autowired
    private HelperFunctions helper;




    // search freelancers by tags, category,

//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
//    @GetMapping(value = "/filter",
//            produces = {"application/json"})
//    public ResponseEntity<?> findAll(@RequestParam(value = "category") String category,
//                                     @RequestParam(value = "tags") List<String> tags) {
//
//
//        List<Freelancer> freebois = freelancerServices.findAllByCategoryOrTagsIn(category, tags);
//        return ResponseEntity.ok(freebois);
//
//    }


    @GetMapping(value = "/freelancer/{email}",
            produces = {"application/json"})
    public ResponseEntity<?> findAll(@PathVariable String email) {
        Freelancer freelancer = freelancerServices.findByEmail(email);
        return new ResponseEntity<>(freelancer,
                HttpStatus.OK);
    }


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
        Freelancer u = freelancerServices.findFreelancerById(id);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // FREELANCER AUTHORIZED  CONSIDER USING AUTHENTICATED USER FOR THIS LATER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PostMapping(value = "/{freelancerid}/post/{postid}")
    public ResponseEntity<?> AddFreelancertopost(
            @PathVariable
                    long freelancerid,
            @PathVariable
                    long postid) throws RestrictionException {

        customerPostService.apply(freelancerid, postid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // FREELANCER AUTHORIZED
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PatchMapping(value = "/freelancer/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updatefreelancer(
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
    @PatchMapping("/upload/{freelancerid}")
        public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable
                long freelancerid) throws IOException {
//            Freelancer guy = freelancerServices.FindFreelancerById(freelancerid);
//            guy.setPicByte(file.getBytes());
//            freelancerServices.save(guy);
        Freelancer newman = new Freelancer();
        newman.setPicByte(file.getBytes());
        freelancerServices.update(newman, freelancerid);
            return ResponseEntity.status(HttpStatus.OK);
    }

}
