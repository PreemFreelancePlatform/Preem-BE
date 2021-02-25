//package com.bzwilson.bflp.models;
//
//import com.sun.istack.NotNull;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "jobprospects")
//public class JobProspects {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    @NotNull
//    @Column(nullable = false)
//    private Double price;
//
//    @Column
//    private String finaloffer;// if freelancer or client is done negotiating lol
//    private String extras
//    private String paytime
//    private String task;
//    private String deliverdate;
//
//    /* freelancer + client need to agree and handshake for job to officially start. controlled by these bools */
//    private boolean freelancerOK;
//    private boolean clientOK;
//
//    @Lob
//    @Column( length = 1000 )
//    private String description;
//}
