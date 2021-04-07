package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tagrequest")
public class TagRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestid;

    @NotNull
    @Column(nullable = false)
    private String category;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @ElementCollection
    private List<String> projects = new ArrayList<>();

    @ElementCollection
    private List<String> githubs = new ArrayList<>();



    @ManyToOne
    @JoinColumn(name = "freelancerid")
    @JsonIgnoreProperties(value = "tagrequest",
            allowSetters = true)
    private Freelancer freelancer;


    public TagRequest() {
    }

    public TagRequest(String category, List<String> tags, List<String> projects, List<String> githubs, Freelancer freelancer) {
        this.category = category;
        this.tags = tags;
        this.projects = projects;
        this.githubs = githubs;
        this.freelancer = freelancer;
    }

    public long getRequestid() {
        return requestid;
    }

    public void setRequestid(long requestid) {
        this.requestid = requestid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getGithubs() {
        return githubs;
    }

    public void setGithubs(List<String> githubs) {
        this.githubs = githubs;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }
}





