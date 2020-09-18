
package com.karlo.model;

import java.math.BigDecimal;


public class ProfessionDetail {
    private int id;
    private String occupation;
    BigDecimal income;

    public ProfessionDetail(int id, String occupation, BigDecimal income) {
        this.id = id;
        this.occupation = occupation;
        this.income = income;
    }
    
    public ProfessionDetail(String occupation, BigDecimal income) {
        this.occupation = occupation;
        this.income = income;
    }

    public ProfessionDetail(String text, String text0) {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
    
}
