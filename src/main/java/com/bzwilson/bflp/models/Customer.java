package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
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
    private long customerid;

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
    private String question1;
    private String security1;
    private String question2;
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

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = {"customer", "contracts"},
            allowSetters = true)
    private List<Contract> contracts = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String password, String LOCKED_role, Boolean tutorial, Boolean setup, Boolean verified, String question1, String security1, String question2, String security2, byte[] picByte) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
        setVerified(verified);
        setQuestion1(question1);
        setSecurity1(security1);
        setQuestion2(question2);
        setSecurity2(security2);
        setPicByte(picByte);
    }

    @JsonView(View.PostInfo.class)
    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    @JsonView(View.PostInfo.class)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonView(View.PostInfo.class)
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

    @JsonView(View.Recovery.class)
    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getSecurity1() {
        return security1;
    }

    public void setSecurity1(String security1) {
        this.security1 = security1;
    }

    @JsonView(View.Recovery.class)
    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getSecurity2() {
        return security2;
    }

    public void setSecurity2(String security2) {
        this.security2 = security2;
    }

    @JsonView(View.PostInfo.class)
    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
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