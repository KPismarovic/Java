package com.karlo.bl;

import com.karlo.model.BasicComplaint;
import com.karlo.model.ContactDetail;
import com.karlo.model.ImportantMedicalComplaint;
import com.karlo.model.LifeStyle;
import com.karlo.model.NextOfKin;
import com.karlo.model.Patient;
import com.karlo.model.PersonAddress;
import com.karlo.model.PersonalDetails;
import com.karlo.model.ProfessionDetail;
import java.util.List;

public class PatientsHandler extends HandlerBase {
    
     public List<Patient> getPatientsForGeneralPhysician(int idGeneralPhysician){
     return repo.getPatientsForGeneralPhysician(idGeneralPhysician);
     }

    public List<Patient> getPatients() {
        return repo.getPatients();
    }

    public int fastRegistryOfPatient(Patient p, BasicComplaint bc, ContactDetail cd, NextOfKin nok) {
        
        return repo.fastRegistyOfPatient(p,bc,cd,nok);
    }

    public Patient getPatient(int idPatient) {
        return repo.getPatient(idPatient);
    }
    
    public void updatePatient(Patient d, int id){
    repo.updatePatient(d, id);
    }

    public void deletePatient(int id) {
        repo.deletePatient(id);
    }
    
    public Patient getDetailedPatient(int id){
        return repo.getDetailedPatient(id);
    }

    public int ComprehensiveRegistryOfPatient(Patient p, ContactDetail cd, NextOfKin nok, BasicComplaint bc, ProfessionDetail prd, LifeStyle ls, ImportantMedicalComplaint imc, PersonalDetails ped, ContactDetail cdnok, PersonAddress pa, PersonAddress pa2, PersonAddress panok) {
      return repo.comprehensiveRegistryOfPatient(p,cd,nok,bc,prd,ls,imc,ped,cdnok,pa,pa2,panok);
    }
    
    
}
