package com.bzwilson.bflp.controllers;
import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerPosts;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.models.TagRequest;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import com.bzwilson.bflp.services.TagRequest.TagRequestServices;
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
@RequestMapping("/tagRequest")
public class TagRequestController {


    @Autowired
    private TagRequestServices tagRequestServices;

    @Autowired
    private FreelancerService freelancerService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/requests",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<TagRequest> myrequests = tagRequestServices.findAll();
        return new ResponseEntity<>(myrequests,
                HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/request/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> findRequestById(
            @PathVariable
                    Long id) {
        TagRequest t = tagRequestServices.findTagRequestById(id);
        return new ResponseEntity<>(t,
                HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_FREELANCER', 'ROLE_ADMIN')")
    @PostMapping(value = "/freelancer/{freelancerid}",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewPost(

            @RequestBody
                    TagRequest newtagrequest,
            @PathVariable long freelancerid)

            throws
            URISyntaxException {

        Freelancer f = freelancerService.findFreelancerById(freelancerid);

        List<TagRequest> list = f.getTagRequests();

        newtagrequest.setFreelancer(f);

        tagRequestServices.save(newtagrequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPropURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{requestid}")
                .buildAndExpand(newtagrequest.getRequestid())
                .toUri();
        responseHeaders.setLocation(newPropURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

}
