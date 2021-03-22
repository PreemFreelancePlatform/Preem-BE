package com.bzwilson.bflp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

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

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "customerposts",
            allowSetters = true)
    private Customer customer;

    @Column
    private String task;

    @JsonView(View.Base.class)
    @Lob
    @Column( length = 1000 )
    private String description;

    @Column
    private String category;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Column
    private Double budget;
    private String duedate;
    private String postdate;

    @ManyToMany()
    @JoinTable(name = "freelancerpost",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "freelancerid"))
            @JsonIgnoreProperties(value = {"customerposts", "freelancers"})
    List<Freelancer> freelancers = new ArrayList<>();

    public CustomerPosts() {
    }

    public CustomerPosts(Customer customer, String task, String description, String category, List<String> tags, Double budget, String duedate, String postdate, List<Freelancer> freelancers) {
        setCustomer(customer);
        setTask(task);
        setDescription(description);
        setCategory(category);
        setTags(tags);
        setBudget(budget);
        setDuedate(duedate);
        setPostdate(postdate);
        setFreelancers(freelancers);
    }

    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    @JsonView(View.Base.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }
}







