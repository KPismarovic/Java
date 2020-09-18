package com.karlo.bl;

import com.karlo.model.Specialist;
import com.karlo.model.Specialization;
import java.util.List;


public class SpecialistsHandler extends HandlerBase {
    public int insertSpecialist(Specialist specialist){
        return repo.insertSpecialist(specialist);
    }
    public Specialist getSpecialist(int idSpecialist){
        return repo.getSpecialist(idSpecialist);
    }
    public List<Specialist> getSpecialists(){
    return repo.getSpecialists();
    }
    public void deleteSpecialist(int id){
        repo.deleteSpecialist(id);
    }
    public Specialization getSpecForSpec(int id){
    return repo.getSpecializationFromSpec(id);
    }
    public void updateSpecialist(Specialist s, int id){
    repo.updateSpecialist(s, id);
    }
}
