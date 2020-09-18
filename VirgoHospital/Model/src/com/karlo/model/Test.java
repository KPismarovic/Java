
package com.karlo.model;

public class Test {
    private int id;
    private String name;
    private String result;
    private Patient patient;

    public Test(int id, String name, String result, Patient patient) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.patient = patient;
    }

    public Test(String name, String result, Patient patient) {
        this.name = name;
        this.result = result;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return name+" | Results: "+ result;
    }
    
}
