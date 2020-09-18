
package com.karlo.model;

public class ImportantMedicalComplaint {
    private int id;
    private String diabetic;
    private String hypertensive;
    private String cardiacCondition;
    private String respiratoryCondition;
    private String digestiveCondition;
    private String orthopedicCondition;
    private String muscularCondition;
    private String neurologicalCondition;
    private String knownAllergies;
    private String adverseReactionToDrugs;
    private String historyOfMajorSurgeries;

    public ImportantMedicalComplaint(int id,String diabetic, String hypertensive, String cardiacCondition, String respiratoryCondition, String digestiveCondition, String orthopedicCondition, String muscularCondition, String neurologicalCondition, String knownAllergies, String adverseReactionToDrugs, String historyOfMajorSurgeries) {
        this.id = id;
        this.diabetic = diabetic;
        this.hypertensive = hypertensive;
        this.cardiacCondition = cardiacCondition;
        this.respiratoryCondition = respiratoryCondition;
        this.digestiveCondition = digestiveCondition;
        this.orthopedicCondition = orthopedicCondition;
        this.muscularCondition = muscularCondition;
        this.neurologicalCondition = neurologicalCondition;
        this.knownAllergies = knownAllergies;
        this.adverseReactionToDrugs = adverseReactionToDrugs;
        this.historyOfMajorSurgeries = historyOfMajorSurgeries;
    }

    
    public ImportantMedicalComplaint(String diabetic, String hypertensive, String cardiacCondition, String respiratoryCondition, String digestiveCondition, String orthopedicCondition, String muscularCondition, String neurologicalCondition, String knownAllergies, String adverseReactionToDrugs, String historyOfMajorSurgeries) {
        this.diabetic = diabetic;
        this.hypertensive = hypertensive;
        this.cardiacCondition = cardiacCondition;
        this.respiratoryCondition = respiratoryCondition;
        this.digestiveCondition = digestiveCondition;
        this.orthopedicCondition = orthopedicCondition;
        this.muscularCondition = muscularCondition;
        this.neurologicalCondition = neurologicalCondition;
        this.knownAllergies = knownAllergies;
        this.adverseReactionToDrugs = adverseReactionToDrugs;
        this.historyOfMajorSurgeries = historyOfMajorSurgeries;
    }

    public String getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(String diabetic) {
        this.diabetic = diabetic;
    }

    public String getHypertensive() {
        return hypertensive;
    }

    public void setHypertensive(String hypertensive) {
        this.hypertensive = hypertensive;
    }

    public String getCardiacCondition() {
        return cardiacCondition;
    }

    public void setCardiacCondition(String cardiacCondition) {
        this.cardiacCondition = cardiacCondition;
    }

    public String getRespiratoryCondition() {
        return respiratoryCondition;
    }

    public void setRespiratoryCondition(String respiratoryCondition) {
        this.respiratoryCondition = respiratoryCondition;
    }

    public String getDigestiveCondition() {
        return digestiveCondition;
    }

    public void setDigestiveCondition(String digestiveCondition) {
        this.digestiveCondition = digestiveCondition;
    }

    public String getOrthopedicCondition() {
        return orthopedicCondition;
    }

    public void setOrthopedicCondition(String orthopedicCondition) {
        this.orthopedicCondition = orthopedicCondition;
    }

    public String getMuscularCondition() {
        return muscularCondition;
    }

    public void setMuscularCondition(String muscularCondition) {
        this.muscularCondition = muscularCondition;
    }

    public String getNeurologicalCondition() {
        return neurologicalCondition;
    }

    public void setNeurologicalCondition(String neurologicalCondition) {
        this.neurologicalCondition = neurologicalCondition;
    }

    public String getKnownAllergies() {
        return knownAllergies;
    }

    public void setKnownAllergies(String knownAllergies) {
        this.knownAllergies = knownAllergies;
    }

    public String getAdverseReactionToDrugs() {
        return adverseReactionToDrugs;
    }

    public void setAdverseReactionToDrugs(String adverseReactionToDrugs) {
        this.adverseReactionToDrugs = adverseReactionToDrugs;
    }

    public String getHistoryOfMajorSurgeries() {
        return historyOfMajorSurgeries;
    }

    public void setHistoryOfMajorSurgeries(String historyOfMajorSurgeries) {
        this.historyOfMajorSurgeries = historyOfMajorSurgeries;
    }
    
    
}
