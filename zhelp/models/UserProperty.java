package com.lambdaschool.foundation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "userproperty")
public class UserProperty extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long propertyid;

    @Column
    private String name;
    private int bedrooms;
    private String neighbourhood;
    private String roomtype;
    private int minimumnights;
    private int numberofreviews;
    private int price;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "userprops",
            allowSetters = true)
    private User userprop;

    public UserProperty() {
    }

    public UserProperty(String name, int bedrooms, String neighbourhood, String roomtype, int minimumnights, int numberofreviews, int price, User userprop) {
        this.name = name;
        this.bedrooms = bedrooms;
        this.neighbourhood = neighbourhood;
        this.roomtype = roomtype;
        this.minimumnights = minimumnights;
        this.numberofreviews = numberofreviews;
        this.price = price;
        this.userprop = userprop;
    }

    public long getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(long propertyid) {
        this.propertyid = propertyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public int getMinimumnights() {
        return minimumnights;
    }

    public void setMinimumnights(int minimumnights) {
        this.minimumnights = minimumnights;
    }

    public int getNumberofreviews() {
        return numberofreviews;
    }

    public void setNumberofreviews(int numberofreviews) {
        this.numberofreviews = numberofreviews;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUserprop() {
        return userprop;
    }

    public void setUserprop(User userprop) {
        this.userprop = userprop;
    }
}
