package com.bzwilson.bflp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "customerpost")
public class CustomerPost {

    // THINGS a customer post needs
    // unique id
    // name
    // description
    // tech stack
    // list of applicants to particular job post

    // this will be a list of posts that a customer will put up
    // many to one relationship with customers


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;


    @Column
    private String name;
    private String description;
    private String tech;


    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "customerpost",
            allowSetters = true)
    private Customer customer;

    public CustomerPost() {
    }

    public CustomerPost(String name, String description, String tech, Customer customer) {
        this.name = name;
        this.description = description;
        this.tech = tech;
        this.customer = customer;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}





