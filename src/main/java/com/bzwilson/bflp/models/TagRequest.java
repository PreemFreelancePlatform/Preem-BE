package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tagrequest")
public class TagRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestid;

    @NotNull
    @Column(nullable = false)
    private String categoryAP4;
    private String tagAP4;
    private String projlink;

    @ManyToOne
    @JoinColumn(name = "freelancerid")
    @JsonIgnoreProperties(value = "tagrequest",
            allowSetters = true)
    private Freelancer freelancer;


    public TagRequest() {
    }

    public TagRequest(String categoryAP4, String tagAP4, String projlink, Freelancer freelancer) {
        this.categoryAP4 = categoryAP4;
        this.tagAP4 = tagAP4;
        this.projlink = projlink;
        this.freelancer = freelancer;
    }

    public long getRequestid() {
        return requestid;
    }

    public void setRequestid(long id) {
        this.requestid = id;
    }

    public String getCategoryAP4() {
        return categoryAP4;
    }

    public void setCategoryAP4(String categoryAP4) {
        this.categoryAP4 = categoryAP4;
    }

    public String getTagAP4() {
        return tagAP4;
    }

    public void setTagAP4(String tagAP4) {
        this.tagAP4 = tagAP4;
    }

    public String getProjlink() {
        return projlink;
    }

    public void setProjlink(String projlink) {
        this.projlink = projlink;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }
}





