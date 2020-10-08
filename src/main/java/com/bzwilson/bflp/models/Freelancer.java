package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @NotNull
    @Column(nullable = false)
    private String LOCKED_role;

    @ManyToMany(mappedBy = "freelancers")
    @JsonIgnoreProperties(value = {"freelancers"})
    private List<CustomerPosts> customerposts = new ArrayList<>();


    public Freelancer() {
    }

    public Freelancer(String email, String username, String firstname, double rating, String password, String LOCKED_role) {
        setEmail(email);
        setUsername(username);
        setFirstname(firstname);
        setRating(rating);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordnoEncrypt(String password) {
        this.password = password;
    }

    public String getLOCKED_role() {
        return LOCKED_role;
    }

    public void setLOCKED_role(String LOCKED_role) {
        this.LOCKED_role = LOCKED_role;
    }

    public List<CustomerPosts> getCustomerposts() {
        return customerposts;
    }

    public void setCustomerposts(List<CustomerPosts> customerposts) {
        this.customerposts = customerposts;
    }

    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority() {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        String myRole = "ROLE_" + this.getLOCKED_role()
                .toUpperCase();
        rtnList.add(new SimpleGrantedAuthority(myRole));
        return rtnList;
    }

}