package com.bzwilson.bflp.controllers;
import com.bzwilson.bflp.models.Customer;
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
@RequestMapping("/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /**
     * Returns a list of all users
     * <a href="http://localhost:2019/users/users" />
     * @return JSON list of all users with a status of OK
     **/

    @GetMapping(value = "/customers",
            produces = {"application/json"})
    public ResponseEntity<?> findAll()
    {
        List<Customer> myCustomers = customerService.findAll();
        return new ResponseEntity<>(myCustomers,
                HttpStatus.OK);
    }


    @GetMapping(value = "/customers/{customerid}",
            produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(
            @PathVariable
                    Long customerid)
    {
        Customer c = customerService.findCustomerById(customerid);
        return new ResponseEntity<>(c,
                HttpStatus.OK);
    }


    @PostMapping(value = "/customer",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(

            @RequestBody
                    Customer newCustomer)
            throws
            URISyntaxException
    {
        newCustomer.setCustomerid(0);
        newCustomer = customerService.save(newCustomer);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{customerid}")
                .buildAndExpand(newCustomer.getCustomerid())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/customer/{customerid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateFullUser(

            @RequestBody
                    Customer updateCustomer,

            @PathVariable
                    long customerid)
    {
        updateCustomer.setCustomerid(customerid);
        customerService.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping(value = "/customer/{customerid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateUser(
            @RequestBody
                    Customer updateCustomer,
            @PathVariable
                    long customerid)
    {
        customerService.update(updateCustomer,
                customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/customer/{customerid}")
    public ResponseEntity<?> deleteUserById(

            @PathVariable
                    long customerid)
    {
        customerService.delete(customerid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
