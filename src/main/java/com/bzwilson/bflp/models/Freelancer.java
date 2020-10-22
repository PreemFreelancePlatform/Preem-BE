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
    @Column(nullable = false,
            unique = true)
    private String email;
    private String username;


    @NotNull
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String LOCKED_role;

    @Lob
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    @Column
    private Boolean tutorial;
    private Boolean setup;

    @ManyToMany(mappedBy = "freelancers")
    @JsonIgnoreProperties(value = {"freelancers"})
    private List<CustomerPosts> customerposts = new ArrayList<>();


    public Freelancer() {
    }

    public Freelancer(String email, String username, String password, String LOCKED_role, byte[] picByte, Boolean tutorial, Boolean setup) {
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setLOCKED_role(LOCKED_role);
        setTutorial(tutorial);
        setSetup(setup);
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

    public byte[] getPicByte() {
        return picByte;
    }
    
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
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