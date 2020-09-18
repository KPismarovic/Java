package com.karlo.bl;

import com.karlo.model.GeneralTreatment;


public class GeneralTreatmentsHandler extends HandlerBase {
    public GeneralTreatment getGeneralTreatment(int id){
        return repo.getGeneralTreatment(id);
    }
    public void updateGeneralTreatment(GeneralTreatment dummy, int id){
        repo.updateGeneralTreatment(dummy,id);
    }
    
            
}
