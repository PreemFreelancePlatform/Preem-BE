package com.bzwilson.bflp.models;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentid;

    private String date;

    private Double payment;

}