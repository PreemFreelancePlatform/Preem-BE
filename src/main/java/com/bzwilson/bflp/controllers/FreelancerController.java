package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class FreelancerController {


    @Autowired
    private FreelancerService freelancerServices;

    @Autowired
    private CustomerPostService customerPostService;


    @GetMapping(value = "/freelancers",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<Freelancer> mylancers = freelancerServices.findAll();

        return new ResponseEntity<>(mylancers,
                HttpStatus.OK);
    }

    @GetMapping(value = "/freelancer/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> FindFreelancerById(
            @PathVariable
                    Long id) {
        Freelancer u = freelancerServices.FindFreelancerById(id);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @PostMapping(value = "/freelancer/{freelancerid}/post/{postid}")
    public ResponseEntity<?> AddFreelancertopost(
            @PathVariable
                    long freelancerid,
            @PathVariable
                    long postid) {


        // do this for adding

        freelancerServices.apply(freelancerid, postid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


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

    @PutMapping(value = "/freelancer/{freelancerid}")
    public ResponseEntity<?> savePost(
            @RequestBody Freelancer freelancer,
            @PathVariable long freelancerid) {
        freelancer.setFreelancerid(freelancerid);
        freelancerServices.save(freelancer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "freelancer/{freelancerid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long freelancerid) {
        freelancerServices.delete(freelancerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
