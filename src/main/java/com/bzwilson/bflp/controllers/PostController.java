package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.View;
import com.bzwilson.bflp.services.CustomerPost.CustomerPostService;
import com.bzwilson.bflp.services.customer.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping(value = "/posts")
    public Page<CustomerPosts> findAll(@RequestParam(value = "page", defaultValue = "0") int page) {
        return postService.findAll(page, 10);
    }

    @JsonView(View.PostInfo.class)
    @PreAuthorize("hasAnyRole('ROLE_FREELANCER', 'ROLE_ADMIN')")
    @GetMapping(value = "/filter",
            produces = {"application/json"})
    public ResponseEntity<?> findAll(@RequestParam(value = "category") List<String> category,
                                     @RequestParam(value = "tags", required = false) List<String> tags,
                                     @RequestParam(value = "min", required = false, defaultValue = "1" ) Double min,
                                     @RequestParam(value = "max", required = false, defaultValue = "10000") Double max,
                                     @RequestParam(value = "page") int page,
                                     @RequestParam(value = "size") int size) {


        if(tags.isEmpty()){
            Pageable pr = PageRequest.of(page, size);
            Page<CustomerPosts> fieldposts = postService.findAllByCategoryInAndBudgetBetween(category, min, max, page, pr);
            return ResponseEntity.ok(fieldposts);
        }

        Pageable pr = PageRequest.of(page, size);
        Page<CustomerPosts> fieldposts = postService.findAllByCategoryInAndTagsInAndBudgetBetween(category, tags, min, max, page, pr);
        return ResponseEntity.ok(fieldposts);
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

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_FREELANCER', 'ROLE_ADMIN')")
    @DeleteMapping(value = "/{pid}/{fid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long pid,
            @PathVariable long fid) {
        postService.removeFreelancerFromPost(pid, fid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}