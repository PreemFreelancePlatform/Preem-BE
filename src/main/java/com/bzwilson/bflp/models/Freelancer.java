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
    private long id;


    @NotNull
    @Column(nullable = false)
    private String email;
    private String username;

    @Column
    private String category;

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

    @ElementCollection
    private List<String> tags = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = "freelancer")
//    @JoinColumn(name = "image")
//    private FreelancerImageModel image;

    @Lob
    @Column
    private byte[] picByte;

    @ManyToMany(mappedBy = "freelancers")
    @JsonIgnoreProperties(value = {"freelancers"})
    private List<CustomerPosts> customerposts = new ArrayList<>();

    public Freelancer() {
    }

    public Freelancer(String email, String username, String category, String password, String LOCKED_role, Boolean tutorial, Boolean setup, List<String> tags, byte[] picByte) {
        setEmail(email);
        setUsername(username);
        setCategory(category);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
        setTags(tags);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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