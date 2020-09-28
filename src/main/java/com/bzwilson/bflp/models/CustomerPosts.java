package com.bzwilson.bflp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customerpost")
public class CustomerPosts {

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
    @JsonIgnoreProperties(value = "customerposts",
            allowSetters = true)
    private Customer customer;

    @ManyToMany(mappedBy = "customerposts")
    @JsonIgnoreProperties(value = {"customerposts"})
    private List<Freelancer> freelancers = new ArrayList<>();


    public CustomerPosts() {
    }

    public CustomerPosts(String name, String description, String tech, Customer customer) {
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

    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }
}






