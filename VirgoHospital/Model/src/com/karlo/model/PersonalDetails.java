
package com.karlo.model;


public class PersonalDetails {
    private int id;
    private String maritalStatus;
    private String noOfDependents;
    private String weight;
    private String bloodType;
    private String height;
    public PersonalDetails(int id,String maritalStatus, String noOfDependents,String height, String weight, String bloodType) {
        this.id = id;
        this.height = height;
        this.maritalStatus = maritalStatus;
        this.noOfDependents = noOfDependents;
        this.weight = weight;
        this.bloodType = bloodType;
    }

     public PersonalDetails(String maritalStatus, String noOfDependents,String height, String weight, String bloodType) {
        this.height = height;
        this.maritalStatus = maritalStatus;
        this.noOfDependents = noOfDependents;
        this.weight = weight;
        this.bloodType = bloodType;
    }
    
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNoOfDependents() {
        return noOfDependents;
    }

    public void setNoOfDependents(String noOfDependents) {
        this.noOfDependents = noOfDependents;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    
}
