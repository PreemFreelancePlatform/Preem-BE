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


    @NotNull
    @Column(nullable = false,
            unique = true)
    private String email;
    private String username;

    @NotNull
    @Column(nullable = false)
    private String firstname;

    @Column
    private double rating;

    @NotNull
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @ManyToMany(mappedBy = "freelancers")
    @JsonIgnoreProperties(value = {"customerposts", "freelancers"})
    private List<CustomerPosts> customerposts = new ArrayList<>();



    public Freelancer() {
    }

    public Freelancer(String email, String username, String firstname, double rating, String password) {
        this.email = email;
        this.username = username;
        this.firstname = firstname;
        this.rating = rating;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomerPosts> getCustomerposts() {
        return customerposts;
    }

    public void setCustomerposts(List<CustomerPosts> customerposts) {
        this.customerposts = customerposts;
    }
}