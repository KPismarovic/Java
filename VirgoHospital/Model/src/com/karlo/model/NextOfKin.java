
package com.karlo.model;


public class NextOfKin extends Person {
    private String middleName;
    private String relationshipWithPatient;
    private ContactDetail contactDetail;
    private PersonAddress contactAddress;
    public NextOfKin(String name, String surname) {
        super(name, surname);
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public PersonAddress getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(PersonAddress contactAddress) {
        this.contactAddress = contactAddress;
    }

    public NextOfKin(int id, String name, String surname,String middleName, String relationshipWithPatient, ContactDetail contactDetail, PersonAddress contactAddress) {
        super(id, name, surname);
        this.middleName = middleName;
        this.relationshipWithPatient = relationshipWithPatient;
        this.contactDetail = contactDetail;
        this.contactAddress = contactAddress;
    }

    public NextOfKin(String name, String surname, String middleName, String relationshipWithPatient) {
        super(name, surname);
        setMiddleName(middleName);
        setRelationshipWithPatient(relationshipWithPatient);
    }

    public NextOfKin( int id,String name, String surname, String middleName, String relationshipWithPatient ) {
        super(id, name, surname);
        setMiddleName(middleName);
        setRelationshipWithPatient(relationshipWithPatient);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getRelationshipWithPatient() {
        return relationshipWithPatient;
    }

    public void setRelationshipWithPatient(String relationshipWithPatient) {
        this.relationshipWithPatient = relationshipWithPatient;
    }
    
    
}
