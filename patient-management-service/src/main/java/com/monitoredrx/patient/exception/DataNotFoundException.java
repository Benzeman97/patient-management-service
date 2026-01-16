package com.monitoredrx.patient.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg){
        super(msg);
    }
}
