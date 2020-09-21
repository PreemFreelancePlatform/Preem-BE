package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer {

    /*
    THINGS Customer needs
    - email
    - first name
    - last name
    - password
    - a list of job posts
        - a list of people that apply for each job

     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerid;

    @Column(nullable = false)
    private String firstname;
    private String lastname;
    private String customeremail;


    @Column(nullable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "customer",
            allowSetters = true)
    private List<CustomerPost> customerpost = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname, String customeremail, String password, List<CustomerPost> customerpost) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.customeremail = customeremail;
        this.password = password;
        this.customerpost = customerpost;
    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomerPost> getCustomerpost() {
        return customerpost;
    }

    public void setCustomerpost(List<CustomerPost> customerpost) {
        this.customerpost = customerpost;
    }
}
