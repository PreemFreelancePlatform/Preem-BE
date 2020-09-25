package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "freelancer")
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long freelancerid;


    @Column
    private String email;
    private String firstname;
    private String lastname;
    private String description;
    private String skills;
    private int rating;


    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne
    @JoinColumn(name = "postid")
    @JsonIgnoreProperties(value = {"freelancers", "customer" },
            allowSetters = true)
    private CustomerPosts customerPost;

    public Freelancer() {
    }

    public Freelancer(String email, String firstname, String lastname, String description, String skills, int rating, String password, CustomerPosts customerPost) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.skills = skills;
        this.rating = rating;
        this.password = password;
        this.customerPost = customerPost;
    }

    public long getFreelancerid() {
        return freelancerid;
    }

    public void setFreelancerid(long freelancerid) {
        this.freelancerid = freelancerid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerPosts getCustomerPost() {
        return customerPost;
    }

    public void setCustomerPost(CustomerPosts customerPost) {
        this.customerPost = customerPost;
    }
}
