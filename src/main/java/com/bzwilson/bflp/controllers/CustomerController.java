package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.Freelancer.FreelancerService;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private FreelancerService freelancerService;


    /**
     * Returns a list of all users
     * <a href="http://localhost:2019/users/users" />
     *
     * @return JSON list of all users with a status of OK
     **/

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/customers",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<Customer> myCustomers = customerService.findAll();
        return new ResponseEntity<>(myCustomers,
                HttpStatus.OK);
    }

    // CUSTOMER SHOULD BE ABLE TO SEE HIMSELF WITHER HERE OR GET AUTHENTICATED USER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_FREELANCER')")
    @GetMapping(value = "/customer/{customerid}",
            produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(
            @PathVariable
                    Long customerid) {
        Customer c = customerService.findCustomerById(customerid);
        return new ResponseEntity<>(c,
                HttpStatus.OK);
    }

    // NEW CUSTOMER IS ALREADY BEING HANDLED

//    @PostMapping(value = "/customer",
//            consumes = {"application/json"})
//    public ResponseEntity<?> addNewCustomer(
//
//            @RequestBody
//                    Customer newCustomer)
//            throws
//            URISyntaxException {
//        newCustomer.setCustomerid(0);
//        newCustomer = customerService.save(newCustomer);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{customerid}")
//                .buildAndExpand(newCustomer.getCustomerid())
//                .toUri();
//        responseHeaders.setLocation(newCustomerURI);
//
//        return new ResponseEntity<>(null,
//                responseHeaders,
//                HttpStatus.CREATED);
//    }

    // CUSTOMER WILL BE ABLE TO UPDATE HIMSELF THRU UPDATE METHOD
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @PutMapping(value = "/customer/{customerid}",
//            consumes = {"application/json"})
//    public ResponseEntity<?> updateFullUser(
//
//            @RequestBody
//                    Customer updateCustomer,
//
//            @PathVariable
//                    long customerid) {
//        updateCustomer.setCustomerid(customerid);
//        customerService.save(updateCustomer);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // CUSTOMER WILL BE ABLE TO UPDATE HIS OWN INFORMATION
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @PatchMapping(value = "/customer/{customerid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateUser(
            @RequestBody
                    Customer updateCustomer,
            @PathVariable
                    long customerid) {
        customerService.update(updateCustomer,
                customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ADMIN ONLY. REFRAIN FROM USING. IF I WANT TO BAN USER THEN I WILL CHANGE ROLE TO BAN
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/customer/{customerid}")
    public ResponseEntity<?> deleteUserById(

            @PathVariable
                    long customerid) {
        customerService.delete(customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PatchMapping(value = "/{cid}/tutorial")
    public ResponseEntity<?> setTut(
            @PathVariable
                    long cid) {
        customerService.didTutorial(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @PatchMapping(value = "/{cid}/setup")
    public ResponseEntity<?> setSetup(
            @PathVariable
                    long cid) {
        customerService.didTutorial(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/upload/{customerid}")
//    public ResponseEntity.BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable
//            long customerid) throws IOException {
//        Customer guy = customerService.findCustomerById(customerid);
//        guy.setPicByte(helper.compressBytes(file.getBytes()));
//        freelancerServices.save(guy);
//        return ResponseEntity.status(HttpStatus.OK);
//
//    }


}
