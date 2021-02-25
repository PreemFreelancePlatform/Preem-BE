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


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(nullable = false)
    private String firstname;
    private String lastname;

    @NotNull
    @Column(nullable = false,
            unique = true)
    private String email;


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
    private Boolean verified;
    private String security1;
    private String security2;


    @Lob
    @Column
    private byte[] picByte;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = {"customer", "freelancerpost"},
            allowSetters = true)
    private List<CustomerPosts> customerposts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String password, String LOCKED_role, Boolean tutorial, Boolean setup, Boolean verified, String security1, String security2, byte[] picByte) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
        setVerified(verified);
        setSecurity1(security1);
        setSecurity2(security2);
        setPicByte(picByte);
    }

    public long getId() {
        return id;
    }

    public void setId(long customerid) {
        this.id = customerid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getSecurity1() {
        return security1;
    }

    public void setSecurity1(String security1) {
        this.security1 = security1;
    }

    public String getSecurity2() {
        return security2;
    }

    public void setSecurity2(String security2) {
        this.security2 = security2;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
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