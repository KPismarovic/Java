
package com.karlo.model;


public class BasicComplaint {
    private int id;
    private String StatementOfComplaint;
    private String HistoryOfPreviousTreatment;
    private String PhysicianOrHospitalTreated;

    public BasicComplaint(int id,String StatementOfComplaint, String HistoryOfPreviousTreatment, String PhysicianOrHospitalTreated) {
        this.id = id;
        setHistoryOfPreviousTreatment(HistoryOfPreviousTreatment);
        setPhysicianOrHospitalTreated(PhysicianOrHospitalTreated);
        setStatementOfComplaint(StatementOfComplaint);
    }
    public BasicComplaint(String StatementOfComplaint, String HistoryOfPreviousTreatment, String PhysicianOrHospitalTreated) {
        
        setHistoryOfPreviousTreatment(HistoryOfPreviousTreatment);
        setPhysicianOrHospitalTreated(PhysicianOrHospitalTreated);
        setStatementOfComplaint(StatementOfComplaint);
    }
    public BasicComplaint(String StatementOfComplaint) {        
        setStatementOfComplaint(StatementOfComplaint);
    }

    public String getStatementOfComplaint() {
        return StatementOfComplaint;
    }

    public void setStatementOfComplaint(String StatementOfComplaint) {
        this.StatementOfComplaint = StatementOfComplaint;
    }

    public String getHistoryOfPreviousTreatment() {
        return HistoryOfPreviousTreatment;
    }

    public void setHistoryOfPreviousTreatment(String HistoryOfPreviousTreatment) {
        this.HistoryOfPreviousTreatment = HistoryOfPreviousTreatment;
    }

    public String getPhysicianOrHospitalTreated() {
        return PhysicianOrHospitalTreated;
    }

    public void setPhysicianOrHospitalTreated(String PhysicianOrHospitalTreated) {
        this.PhysicianOrHospitalTreated = PhysicianOrHospitalTreated;
    }
    
}
