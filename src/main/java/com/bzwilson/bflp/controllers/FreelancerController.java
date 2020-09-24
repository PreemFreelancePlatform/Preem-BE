package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.Freelancer.FreelancerServices;
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
    private FreelancerServices freelancerServices;


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

    @PostMapping(value = "/freelancer/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> AddFreelancer(

            @RequestBody
                    Freelancer newfreelancer,
            @PathVariable long id)

            throws
            URISyntaxException {

        Freelancer u = freelancerServices.FindFreelancerById(id);

        List<CustomerPosts> list = u.getCustomerposts();

        newcustomerpost.setCustomer(u);

        postService.save(newcustomerpost);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPropURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postid}")
                .buildAndExpand(newcustomerpost.getPostid())
                .toUri();
        responseHeaders.setLocation(newPropURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/post/{postid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updatePost(
            @RequestBody
                    CustomerPosts customerpost,
            @PathVariable
                    long postid) {
        postService.update(customerpost,
                postid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/post/{postid}")
    public ResponseEntity<?> savePost(
            @RequestBody CustomerPosts post,
            @PathVariable long postid)
    {
        post.setPostid(postid);
        postService.save(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "post/{postid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long postid)
    {
        postService.delete(postid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
