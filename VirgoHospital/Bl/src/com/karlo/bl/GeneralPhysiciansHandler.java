package com.karlo.bl;

import com.karlo.model.GeneralPhysician;
import java.util.Comparator;
import java.util.List;

public class GeneralPhysiciansHandler extends HandlerBase {
    public int insertGeneralPhysician(GeneralPhysician GeneralPhysician){
    return repo.insertGeneralPhysician(GeneralPhysician);
    }
    
    public void updateGeneralPhysician(GeneralPhysician dummy, int idGeneralPhysician){
    repo.updateGeneralPhysician(dummy, idGeneralPhysician);
    }
    
    public void deleteGeneralPhysician(int idGeneralPhysician){
    repo.deleteGeneralPhysician(idGeneralPhysician);
    }
    
    public GeneralPhysician getGeneralPhysician(int idGeneralPhysician){
        return repo.getGeneralPhysician(idGeneralPhysician);
    }
    
    public List<GeneralPhysician> getGeneralPhysicians(){
    return repo.getGeneralPhysicians();
    }
    public List<GeneralPhysician> getGeneralPhysiciansSortedBySurname(){
        List<GeneralPhysician> GeneralPhysicians = repo.getGeneralPhysicians();
        GeneralPhysicians.sort((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
        
    return GeneralPhysicians;
    }
    public List<GeneralPhysician> getGeneralPhysiciansSortedByName(){
        List<GeneralPhysician> GeneralPhysicians = repo.getGeneralPhysicians();
        GeneralPhysicians.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        
    return GeneralPhysicians;
    }

    
    
}
