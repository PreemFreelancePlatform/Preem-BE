package com.bzwilson.bflp.controllers;


import com.bzwilson.bflp.models.Contract;
import com.bzwilson.bflp.models.Freelancer;
import com.bzwilson.bflp.services.Contracts.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {


    @Autowired
    private ContractService contractService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    @GetMapping(value = "/contracts",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<Contract> contracts = contractService.findAll();
        return new ResponseEntity<>(contracts,
                HttpStatus.OK);
    }

    // FREELANCER AUTHORIZED IF I WANT TO GET MYSELF USE CURRENTLY AUTHENTICATED METHOD
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> findcontractbyid(
            @PathVariable
                    Long id) {
        Contract c = contractService.findContractById(id);
        return new ResponseEntity<>(c,
                HttpStatus.OK);
    }

    // FREELANCER AUTHORIZED  CONSIDER USING AUTHENTICATED USER FOR THIS LATER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PostMapping(value = "/to/{fid}/{cid}")
    public ResponseEntity<?> makenewcontract(
            @PathVariable
                    long fid,
            @PathVariable
                    long cid,
            @RequestBody
                    Contract contract) {

        contractService.createnew(contract, fid, cid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // FREELANCER AUTHORIZED
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
    @PatchMapping(value = "/{id}",
            consumes = {"application/json"})
    public ResponseEntity<?> updatecontract(
            @RequestBody
                    Contract contract,
            @PathVariable
                    long id) {
        contractService.update(contract,
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
    @DeleteMapping(value = "/{contractid}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long contractid) {
        contractService.delete(contractid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
