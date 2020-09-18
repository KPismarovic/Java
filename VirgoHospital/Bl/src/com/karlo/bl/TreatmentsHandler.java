package com.karlo.bl;

import com.karlo.model.Treatment;


public class TreatmentsHandler extends HandlerBase {
    public Treatment getTreatment(int id){
        return repo.getTreatment(id);
    }
    public void updateTreatment(Treatment dummy, int id){
        repo.updateTreatment(dummy,id);
    }
    
}
