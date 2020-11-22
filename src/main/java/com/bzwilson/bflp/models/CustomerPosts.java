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
    private String task;
    private String description;
    private String field;
    private String specialization;
    private String budget;
    private String duedate;

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "customerposts",
            allowSetters = true)
    private Customer customer;

    @ManyToMany()
    @JoinTable(name = "freelancerpost",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "freelancerid"))
    List<Freelancer> freelancers = new ArrayList<>();

    public CustomerPosts() {
    }

    public CustomerPosts(String task, String description, String field, String specialization, String budget, String duedate, Customer customer) {
        this.task = task;
        this.description = description;
        this.field = field;
        this.specialization = specialization;
        this.budget = budget;
        this.duedate = duedate;
        this.customer = customer;
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
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






