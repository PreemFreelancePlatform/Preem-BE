package com.lambdaschool.foundation.controllers;


import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserProperty;
import com.lambdaschool.foundation.services.UserService;
import com.lambdaschool.foundation.services.UserpropertyService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private UserpropertyService propertyService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/properties",
            produces = {"application/json"})
    public ResponseEntity<?> findAll() {
        List<UserProperty> myprops = propertyService.findAllProperties();

        return new ResponseEntity<>(myprops,
                HttpStatus.OK);
    }

    @GetMapping(value = "/property/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> findPropertyById(
            @PathVariable
                    Long id) {
        UserProperty u = propertyService.findPropertyById(id);
        return new ResponseEntity<>(u,
                HttpStatus.OK);
    }

    @PostMapping(value = "/user/{userid}/property",
            consumes = {"application/json"})
    public ResponseEntity<?> addNewProp(
            @Valid
            @RequestBody
                    UserProperty newprop,
            @PathVariable long userid)

            throws
            URISyntaxException {


        User u = userService.findUserById(userid);

        List<UserProperty> list = u.getUserprops();
        newprop.setUserprop(u);

        propertyService.save(newprop);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPropURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{propertyid}")
                .buildAndExpand(newprop.getPropertyid())
                .toUri();
        responseHeaders.setLocation(newPropURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/property/{propid}",
            consumes = {"application/json"})
    public ResponseEntity<?> updateProp(
            @RequestBody
                    UserProperty property,
            @PathVariable
                    long propid) {
        propertyService.update(property,
                propid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/property/{id}")
    public ResponseEntity<?> saveProp(
            @RequestBody UserProperty property,
            @PathVariable long id)
    {
        propertyService.update(property, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "property/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        propertyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
