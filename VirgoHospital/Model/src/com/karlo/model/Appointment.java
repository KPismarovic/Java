package com.karlo.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


public class Appointment {
    
    private int id;
    private Date date;
    private Payment payment;
    

    public Appointment(int id, Date date, Payment payment) {
        this.id = id;
        this.date = date;
        this.payment = payment;
        
    }

    

    public Appointment(Date date) {
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
           LocalDate dat =
                    Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return dat.toString();
        
    }
    
    
}
