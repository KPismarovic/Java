
package com.karlo.model;

public class Specialist extends Person{
     private String title;
     private Specialization specialization;

    public Specialist(String name, String surname,String title, Specialization specialization) {
        super(name, surname);
        this.title = title;
        this.specialization = specialization;
    }

    public Specialist(int id, String name, String surname, String title, Specialization specialization){
    
        super(id,name, surname);
        this.title = title;
        this.specialization = specialization;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title+' '+super.getName()+' '+ super.getSurname() + ", "+specialization.getName();
    }
    
}
