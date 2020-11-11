//package com.bzwilson.bflp.models;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "imagemodel")
//public class FreelancerImageModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long imageid;
//
//    @Column
//    private String name;
//
//    @Column
//    private String type;
//
//
//    @OneToOne(mappedBy = "image")
//    @JsonIgnoreProperties(value = {"imagemodel"})
//    private Freelancer freelancer;
//
//    //image bytes can have large lengths so we specify a value
//    //which is more than the default length for picByte column
//
//    @Lob
//    @Column
//    private byte[] picByte;
//
//    public FreelancerImageModel() {
//    }
//
//    public FreelancerImageModel(String name, String type, byte[] picByte) {
//        this.name = name;
//        this.type = type;
//        this.picByte = picByte;
//    }
//
//    public long getImageid() {
//        return imageid;
//    }
//
//    public void setImageid(long imageid) {
//        this.imageid = imageid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Freelancer getFreelancer() {
//        return freelancer;
//    }
//
//    public void setFreelancer(Freelancer freelancer) {
//        this.freelancer = freelancer;
//    }
//
//    public byte[] getPicByte() {
//        return picByte;
//    }
//
//    public void setPicByte(byte[] picByte) {
//        this.picByte = picByte;
//    }
//}
