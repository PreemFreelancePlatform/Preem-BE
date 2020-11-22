package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/customer/post")
public class PostController {

    @Autowired
    private CustomerPostService postService;

    @Autowired
    private CustomerService customerService;


    @PreAuthorize("hasAnyRole('ROLE_FREELANCER', 'ROLE_ADMIN')")
    @GetMapping(value = "/posts",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<CustomerPosts> myPosts = postService.findAll();

        return new ResponseEntity<>(myPosts,
                HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ROLE_FREELANCER', 'ROLE_ADMIN')")
    @GetMapping(value = "/field/{input}",
            produces = {"application/json"})
    public ResponseEntity<?> findAll(@PathVariable String input) {
        List<CustomerPosts> fieldposts = postService.findAllByField(input);
        return new ResponseEntity<>(fieldposts,
                HttpStatus.OK);
    }


    // CUSTOMER ONLY
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{postid}",
            produces = {"application/json"})
    public ResponseEntity<?> findPostById(
            @PathVariable
                    Long postid) {
        CustomerPosts u = postService.findByCustomerPostId(postid);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    // CUSTOMER ONLY
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @PostMapping(value = "/customer/{customerid}",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewPost(

            @RequestBody
                    CustomerPosts newcustomerpost,
            @PathVariable long customerid)

            throws
            URISyntaxException {

        Customer u = customerService.findCustomerById(customerid);

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
    
    // CUSTOMER ONLY
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @PatchMapping(value = "/{postid}",
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

    // CUSTOMER CAN EITHER DELETE POST OR WILL AUTO DELETE AFTER FREELANCER IS HIRED
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @DeleteMapping(value = "/{postid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long postid) {
        postService.delete(postid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}