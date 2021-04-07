package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "contracts",
            allowSetters = true)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "freelancerid")
    @JsonIgnoreProperties(value = "contracts",
            allowSetters = true)
    private Freelancer freelancer;

    @Column(nullable = false)
    private Double price;

    @Column
    private String finaloffer;// if freelancer or client is done negotiating lol
    private String extras;
    private String paytime;
    private String task;
    private String deliverdate;

    @Lob
    @Column( length = 1000 )
    private String description;

    @Column
    private boolean freelancerOK;
    private boolean clientOK;
    private boolean active;

    public Contract() {
    }

    public Contract(Double price, String finaloffer, String extras, String paytime, String task, String deliverdate, String description, boolean freelancerOK, boolean clientOK, boolean active) {
        setPrice(price);
        setFinaloffer(finaloffer);
        setExtras(extras);
        setPaytime(paytime);
        setTask(task);
        setDeliverdate(deliverdate);
        setDescription(description);
        setFreelancerOK(false);
        setClientOK(false);
        setActive(false);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFinaloffer() {
        return finaloffer;
    }

    public void setFinaloffer(String finaloffer) {
        this.finaloffer = finaloffer;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(String deliverdate) {
        this.deliverdate = deliverdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFreelancerOK() {
        return freelancerOK;
    }

    public void setFreelancerOK(boolean freelancerOK) {
        this.freelancerOK = freelancerOK;
    }

    public boolean getClientOK() {
        return clientOK;
    }

    public void setClientOK(boolean clientOK) {
        this.clientOK = clientOK;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

