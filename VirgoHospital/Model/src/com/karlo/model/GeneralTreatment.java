
package com.karlo.model;


public class GeneralTreatment {
    private int id;
    private String tot;
    private GeneralPhysician gp;

    public GeneralTreatment(int id, String tot, GeneralPhysician gp) {
        this.id = id;
        this.tot = tot;
        this.gp = gp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTot() {
        return tot;
    }

    public void setTot(String tot) {
        this.tot = tot;
    }

    public GeneralPhysician getGp() {
        return gp;
    }

    public void setGp(GeneralPhysician gp) {
        this.gp = gp;
    }
    
    
}
