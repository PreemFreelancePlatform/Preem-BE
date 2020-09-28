package com.bzwilson.bflp.controllers;

import com.bzwilson.bflp.models.Customer;
import com.bzwilson.bflp.models.CustomerMin;
import com.bzwilson.bflp.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class OpenCustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping(value = "/createnewcustomer",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addCustomer(
            HttpServletRequest httpServletRequest,
            @RequestBody
                    CustomerMin newmincustomer)
            throws
            URISyntaxException {
        // Create the user
        Customer newcustomer = new Customer();

        newcustomer.setUsername(newmincustomer.getUsername());
        newcustomer.setPassword(newmincustomer.getPassword());
        newcustomer.setCustomeremail(newmincustomer.getCustomeremail());

        // add the default role of user
//        List<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newuser,
//                roleService.findByName("user")));
//        newuser.setRoles(newRoles);

        newcustomer = customerService.save(newcustomer);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/customer/{customerid}")
                .buildAndExpand(newcustomer.getCustomerid())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(
                responseHeaders,
                HttpStatus.CREATED);
    }

    // return the access token
    // To get the access token, surf to the endpoint /login just as if a client had done this.
//        RestTemplate restTemplate = new RestTemplate();
//        String requestURI = "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/login";
//
//        List<MediaType> acceptableMediaTypes = new ArrayList<>();
//        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setAccept(acceptableMediaTypes);
//        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
//                             System.getenv("OAUTHCLIENTSECRET"));
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("grant_type",
//                "password");
//        map.add("scope",
//                "read write trust");
//        map.add("username",
//                newminuser.getUsername());
//        map.add("password",
//                newminuser.getPassword());
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
//                                                                             headers);
//
//        String theToken = restTemplate.postForObject(requestURI,
//                                                     request,
//                                                     String.class);

//        return new ResponseEntity<>(theToken,
//                                    responseHeaders,
//                                    HttpStatus.CREATED);
//    }

    /**
     * Prevents no favicon.ico warning from appearing in the logs. @ApiIgnore tells Swagger to ignore documenting this as an endpoint.
     */
//    @ApiIgnore
//    @GetMapping("favicon.ico")
//    public void returnNoFavicon()
//    {
//
//    }
}
