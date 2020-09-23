//package com.bzwilson.bflp.models;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.sun.istack.NotNull;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "freelancer")
//public class Freelancer {
//     /*
//    THINGS freelancers need
//
//    - email
//    - first name
//    - last name
//    - description
//    - tech
//    - skills
//    - password
//    - rating
//    - many to one relationship to customerposts
//
//     */
//
//    @Column
//    private String email;
//    private String firstname;
//    private String lastname;
//    private String description;
//    private String tech;
//    private String skills;
//    private int rating;
//
//    @NotNull
//    @Column(nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String password;
//
//
//    //many to one with posts
//
//
//}
