
package com.karlo.model;


public class PersonAddress {
    private int id;
    private String doorNumber;
    private String street;
    private String area;
    private String city;
    private String state;
    private String pincode;

    public PersonAddress(int id, String doorNumber, String street, String area, String city, String state, String pincode) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
    
    public PersonAddress( String doorNumber, String street, String area, String city, String state, String pincode) {
        
        this.doorNumber = doorNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    
}
