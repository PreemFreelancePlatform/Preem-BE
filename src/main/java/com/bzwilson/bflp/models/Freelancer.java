package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Bool;
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
    private long id;


    @NotNull
    @Column(nullable = false)
    private String email;
    private String firstname;
    private String lastname;

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


    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ElementCollection
    private List<String> categories = new ArrayList<>();


    @Lob
    @Column
    private byte[] picByte;

    @ManyToMany(mappedBy = "freelancers")
    @JsonIgnoreProperties(value = {"freelancers"})
    private List<CustomerPosts> customerposts = new ArrayList<>();

    public Freelancer() {
    }

    public Freelancer(String email, String firstname, String lastname, String password, String LOCKED_role, Boolean tutorial, Boolean setup, Boolean verified, String security1, String security2, List<String> tags, List<String> categories, byte[] picByte) {
        setEmail(email);
        setFirstname(firstname);
        setLastname(lastname);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
        setVerified(verified);
        setSecurity1(security1);
        setSecurity2(security2);
        setTags(tags);
        setCategories(categories);
        setPicByte(picByte);
    }

    public long getId() {
        return id;
    }

    public void setId(long freelancerid) {
        this.id = freelancerid;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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