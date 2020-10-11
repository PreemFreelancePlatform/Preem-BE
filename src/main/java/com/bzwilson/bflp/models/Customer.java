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
@Table(name = "customer")
public class Customer {

// make stuff unique and encrypt password

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerid;


    @NotNull
    @Column(nullable = false,
            unique = true)
    private String username;
    private String customeremail;


    @NotNull
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String LOCKED_role;

    @Column
    private Boolean tutorial;
    private Boolean setup;


    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = {"customer", "freelancerpost"},
            allowSetters = true)
    private List<CustomerPosts> customerposts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String username, String customeremail, String password, String LOCKED_role, Boolean tutorial, Boolean setup) {
        setUsername(username);
        setCustomeremail(customeremail);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public void setPasswordNoEncrypt(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getLOCKED_role() {
        return LOCKED_role;
    }

    public void setLOCKED_role(String LOCKED_role) {
        this.LOCKED_role = LOCKED_role;
    }

    public Boolean getTutorial() {
        return tutorial;
    }

    public void setTutorial(Boolean tutorial) {
        this.tutorial = tutorial;
    }

    public Boolean getSetup() {
        return setup;
    }

    public void setSetup(Boolean setup) {
        this.setup = setup;
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