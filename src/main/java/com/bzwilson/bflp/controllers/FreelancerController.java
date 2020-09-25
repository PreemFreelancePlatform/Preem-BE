package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;

import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/freelancer")
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

    @PostMapping(value = "/post/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> AddFreelancer(

            @RequestBody
                    Freelancer newfreelancer,
            @PathVariable long id)

            throws
            URISyntaxException {

        // do this for adding
        CustomerPosts cp = customerPostService.findByCustomerPostId(id);

        newfreelancer.setCustomerPost(cp);

        freelancerServices.save(newfreelancer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPropURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postid}")
                .buildAndExpand(newfreelancer.getFreelancerid())
                .toUri();
        responseHeaders.setLocation(newPropURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
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

//    @PutMapping(value = "/post/{postid}")
//    public ResponseEntity<?> savePost(
//            @RequestBody CustomerPosts post,
//            @PathVariable long postid)
//    {
//        post.setPostid(postid);
//        postService.save(post);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @DeleteMapping(value = "post/{postid}")
//    public ResponseEntity<?> deleteUserById(
//            @PathVariable
//                    long postid)
//    {
//        postService.delete(postid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



}
