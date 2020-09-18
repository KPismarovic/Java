package com.karlo.model;

public class GeneralPhysician extends Person{
    private String title;
    public GeneralPhysician(String name, String surname,String title) {
        super(name, surname);
        setTitle(title);
    }

    public GeneralPhysician(int id) {
        super(id);
    }

    public GeneralPhysician(int id, String name, String surname, String title) {
        this(name,surname,title);
        setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  title+' '+super.getName() + ' '+super.getSurname();
    }
    
}
