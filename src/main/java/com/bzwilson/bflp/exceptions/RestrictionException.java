package com.bzwilson.bflp.exceptions;

public class RestrictionException extends RuntimeException {
    public RestrictionException(String message) {
        super(" Restricted action " + message);
    }
}
