package com.karlo.model;
import java.sql.Timestamp;

public class Patient extends Person{
    
    private Timestamp dateOfCreation;
    private String dob;
    private String sex;
    private ContactDetail contact;
    private ProfessionDetail profDetails;
    private BasicComplaint basicComp;
    private ImportantMedicalComplaint imc;
    private LifeStyle lifestyle;
    private NextOfKin nok;
    public Patient(String name, String surname) {
        super(name, surname);
        
    }

    public Patient(int id, String name, String surname, Timestamp dateOfCreation, String dob, String sex, ContactDetail contact, ProfessionDetail profDetails, BasicComplaint basicComp, ImportantMedicalComplaint imc, LifeStyle lifestyle, NextOfKin nok) {
        super(id, name, surname);
        this.dateOfCreation = dateOfCreation;
        this.dob = dob;
        this.sex = sex;
        this.contact = contact;
        this.profDetails = profDetails;
        this.basicComp = basicComp;
        this.imc = imc;
        this.lifestyle = lifestyle;
        this.nok = nok;
    }

    public ContactDetail getContact() {
        return contact;
    }

    public void setContact(ContactDetail contact) {
        this.contact = contact;
    }

    public ProfessionDetail getProfDetails() {
        return profDetails;
    }

    public void setProfDetails(ProfessionDetail profDetails) {
        this.profDetails = profDetails;
    }

    public BasicComplaint getBasicComp() {
        return basicComp;
    }

    public void setBasicComp(BasicComplaint basicComp) {
        this.basicComp = basicComp;
    }

    public ImportantMedicalComplaint getImc() {
        return imc;
    }

    public void setImc(ImportantMedicalComplaint imc) {
        this.imc = imc;
    }

    public LifeStyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(LifeStyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public NextOfKin getNok() {
        return nok;
    }

    public void setNok(NextOfKin nok) {
        this.nok = nok;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public Patient(int id) {
        super(id);
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Patient(Timestamp dateOfCreation, String dob, String sex, String name, String surname) {
        super(name, surname);
        this.dateOfCreation = dateOfCreation;
        this.dob = dob;
        this.sex = sex;
    }
    public Patient(String name, String surname, String sex,String dob) {
        super(name, surname);
        this.dob = dob;
        this.sex = sex;
    }

    public Patient(int id,String name, String surname, String sex, String dob, Timestamp dateOfCreation) {
        this(name, surname);
        setId(id);
        this.dateOfCreation = dateOfCreation;
        setSex(sex);
        setDob(dob);
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

   

    public String getSex() {
        return sex;
    }

    public void setSex(String gender) {
        this.sex = gender;
    }

    @Override
    public String toString() {
//        return super.toString() + "Sex: " + sex + " | Date of Birth: "+dob;
  return super.toString();
    }
    
    
}
