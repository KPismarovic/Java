package com.karlo.bl;

import com.karlo.model.Specialization;
import java.util.List;


public class SpecializationsHandler extends HandlerBase {
    public List<Specialization> getSpecializations(){
        return repo.getSpecializations();
    }

    public Specialization getSpecialization(int idSpec) {
        return repo.getSpecialization(idSpec);
    }
    public void insertSpecialization(Specialization spec){
        repo.insertSpecialization(spec);
        
    }
    public void updateSpecialization(Specialization dummy, int id){
        repo.updateSpecialization(dummy,id);
        
    }
}
