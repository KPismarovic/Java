
package com.karlo.model;

import java.math.BigDecimal;


public class Payment {
    private int id;
    private BigDecimal cost;

    public Payment(int id, BigDecimal cost) {
        this.id = id;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
}
