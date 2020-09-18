package com.karlo.bl;

import com.karlo.model.MedicalPerscription;
import com.karlo.model.Patient;
import java.util.List;


public class MedicalPerscriptionsHandler extends HandlerBase {
    public MedicalPerscription getMedicalPerscription(int id){
        return repo.getMedicalPerscription(id);
    }
    public void updateMedicalPerscription(MedicalPerscription dummy, int id){
        repo.updateMedicalPerscription(dummy,id);
    }
    public int insertMedicalPersciption(MedicalPerscription med ){
        return repo.insertMedicalPerscription(med);
    }
    public List<MedicalPerscription> getMedicalPerscriptionsForPatient(Patient p){
        return repo.getMedicalPerscriptionsForPatient(p);
    }
}
