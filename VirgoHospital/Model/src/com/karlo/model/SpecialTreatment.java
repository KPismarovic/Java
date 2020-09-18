
package com.karlo.model;

public class SpecialTreatment {
    private int id;
    private String tot;
    private Specialist spec;

    public SpecialTreatment(int id, String tot, Specialist spec) {
        this.id = id;
        this.tot = tot;
        this.spec = spec;
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

    public Specialist getSpec() {
        return spec;
    }

    public void setSpec(Specialist spec) {
        this.spec = spec;
    }
    
}
