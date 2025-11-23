package com.airtripe.studymanagement.exception;

public class CourcesNotFoundException extends RuntimeException{
    public CourcesNotFoundException(String id) {
        super("Course with id " + id + " not found");
    }
}
