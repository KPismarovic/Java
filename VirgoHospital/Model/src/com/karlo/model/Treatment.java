
package com.karlo.model;


public class Treatment {
    private int id;
    private Patient patient;
    private SpecialTreatment st;
    private GeneralTreatment gt;
    private Appointment appointment;
    private MedicalPerscription mp;
    private String diagnosis;

    public Treatment(int id, Patient patient, SpecialTreatment st, GeneralTreatment gt, Appointment appointment, MedicalPerscription mp, String diagnosis) {
        this.id = id;
        this.patient = patient;
        this.st = st;
        this.gt = gt;
        this.appointment = appointment;
        this.mp = mp;
        this.diagnosis = diagnosis;
    }

    public Treatment(Patient patient, GeneralTreatment gt, Appointment appointment) {
        this.patient = patient;
        this.gt = gt;
        this.appointment = appointment;
    }

    public Treatment(MedicalPerscription m) {
        this.mp = m;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public SpecialTreatment getSt() {
        return st;
    }

    public void setSt(SpecialTreatment st) {
        this.st = st;
    }

    public GeneralTreatment getGt() {
        return gt;
    }

    public void setGt(GeneralTreatment gt) {
        this.gt = gt;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public MedicalPerscription getMp() {
        return mp;
    }

    public void setMp(MedicalPerscription mp) {
        this.mp = mp;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
}
