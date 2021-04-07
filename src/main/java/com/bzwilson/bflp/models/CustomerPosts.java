package com.bzwilson.bflp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customerpost")
public class CustomerPosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postid;

    @Column
    private String task;

    @Lob
    @Column( length = 1000 )
    private String description;

    @Column
    private Double budget;
    private String duedate;
    private String postdate;
    private String category;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "freelancerpost",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "freelancerid"))
            @JsonIgnoreProperties(value = {"customerposts", "freelancers"})
    List<Freelancer> freelancers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "customerposts",
            allowSetters = true)
    private Customer customer;


    public CustomerPosts() {
    }

    public CustomerPosts(String task, String description, Double budget, String duedate, String postdate, String category, List<String> tags, List<Freelancer> freelancers, Customer customer) {
        setTask(task);
        setDescription(description);
        setBudget(budget);
        setDuedate(duedate);
        setPostdate(postdate);
        setCategory(category);
        setTags(tags);
        setFreelancers(freelancers);
        setCustomer(customer);
    }

    @JsonView(View.PostInfo.class)
    public long getPostid() {
        return postid;
    }

    public void setPostid(long postid) {
        this.postid = postid;
    }

    @JsonView(View.PostInfo.class)
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @JsonView(View.PostInfo.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(View.PostInfo.class)
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @JsonView(View.PostInfo.class)
    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    @JsonView(View.PostInfo.class)
    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }


    @JsonView(View.PostInfo.class)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonView(View.PostInfo.class)
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Freelancer> getFreelancers() { return freelancers; }

    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }

    @JsonView(View.PostInfo.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}







