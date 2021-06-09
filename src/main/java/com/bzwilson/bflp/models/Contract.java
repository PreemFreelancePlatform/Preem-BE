package com.bzwilson.bflp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid")
    @JsonIgnoreProperties(value = "contracts",
            allowSetters = true)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "freelancerid")
    @JsonIgnoreProperties(value = "contracts",
            allowSetters = true)
    private Freelancer freelancer;

    @Column(nullable = false)
    private Double price;

    @Column
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

    public Contract(Customer customer , Freelancer freelancer, Double price, String paytime, String task, String deliverdate, String description, boolean freelancerOK, boolean clientOK, boolean active) {
        setCustomer(customer);
        setFreelancer(freelancer);
        setPrice(price);
        setPaytime(paytime);
        setTask(task);
        setDeliverdate(deliverdate);
        setDescription(description);
        setFreelancerOK(false);
        setClientOK(false);
        setActive(false);
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonView(View.Confusion2.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonView(View.Confusion1.class)
    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public String getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(String deliverdate) {
        this.deliverdate = deliverdate;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public boolean getFreelancerOK() {
        return freelancerOK;
    }

    public void setFreelancerOK(boolean freelancerOK) {
        this.freelancerOK = freelancerOK;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public boolean getClientOK() {
        return clientOK;
    }

    public void setClientOK(boolean clientOK) {
        this.clientOK = clientOK;
    }

    @JsonView({View.Confusion1.class, View.Confusion2.class})
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

