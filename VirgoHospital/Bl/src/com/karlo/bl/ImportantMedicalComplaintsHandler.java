package com.karlo.bl;

import com.karlo.model.ImportantMedicalComplaint;


public class ImportantMedicalComplaintsHandler extends HandlerBase{
    public ImportantMedicalComplaint getImportantMedicalComplaint(int id){
        return repo.getImportantMedicalComplaint(id);
    }
    public void updateImportantMedicalComplaint(ImportantMedicalComplaint dummy, int id){
        repo.updateImportantMedicalComplaint(dummy,id);
    }
    public int insertImportantMedicalComplaint(ImportantMedicalComplaint imc){
        return repo.insertImportantMedComp(imc);
    }
}
