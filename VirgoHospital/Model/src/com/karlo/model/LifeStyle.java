
package com.karlo.model;


public class LifeStyle {
    private int id;
    private String vegetarian;
    private String smoker;
    private String alcohol;
    private String stimulans;
    private String consupmtionOfCoffe;
    private String consumptionOfSoftDrinks;
    private String regularityOfMeals;
    private String eatingHabits;

    public LifeStyle(int id,String vegetarian, String smoker, String alcohol, String stimulans, String consupmtionOfCoffe, String consumptionOfSoftDrinks, String regularityOfMeals, String eatingHabits) {
        this.id = id;
        this.vegetarian = vegetarian;
        this.smoker = smoker;
        this.alcohol = alcohol;
        this.stimulans = stimulans;
        this.consupmtionOfCoffe = consupmtionOfCoffe;
        this.consumptionOfSoftDrinks = consumptionOfSoftDrinks;
        this.regularityOfMeals = regularityOfMeals;
        this.eatingHabits = eatingHabits;
    }
    
    public LifeStyle(String vegetarian, String smoker, String alcohol, String stimulans, String consupmtionOfCoffe, String consumptionOfSoftDrinks, String regularityOfMeals, String eatingHabits) {
        this.vegetarian = vegetarian;
        this.smoker = smoker;
        this.alcohol = alcohol;
        this.stimulans = stimulans;
        this.consupmtionOfCoffe = consupmtionOfCoffe;
        this.consumptionOfSoftDrinks = consumptionOfSoftDrinks;
        this.regularityOfMeals = regularityOfMeals;
        this.eatingHabits = eatingHabits;
    }

    public String getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    public String getSmoker() {
        return smoker;
    }

    public void setSmoker(String smoker) {
        this.smoker = smoker;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getStimulans() {
        return stimulans;
    }

    public void setStimulans(String stimulans) {
        this.stimulans = stimulans;
    }

    public String getConsupmtionOfCoffe() {
        return consupmtionOfCoffe;
    }

    public void setConsupmtionOfCoffe(String consupmtionOfCoffe) {
        this.consupmtionOfCoffe = consupmtionOfCoffe;
    }

    public String getConsumptionOfSoftDrinks() {
        return consumptionOfSoftDrinks;
    }

    public void setConsumptionOfSoftDrinks(String consumptionOfSoftDrinks) {
        this.consumptionOfSoftDrinks = consumptionOfSoftDrinks;
    }

    public String getRegularityOfMeals() {
        return regularityOfMeals;
    }

    public void setRegularityOfMeals(String regularityOfMeals) {
        this.regularityOfMeals = regularityOfMeals;
    }

    public String getEatingHabits() {
        return eatingHabits;
    }

    public void setEatingHabits(String eatingHabits) {
        this.eatingHabits = eatingHabits;
    }
    
    
    
}
