package com.karlo.bl;

import com.karlo.model.SpecialTreatment;


public class SpecialTreatmentsHandler extends HandlerBase{
    public SpecialTreatment getSpecialTreatment(int id){
        return repo.getSpecialTreatment(id);
    }
    public void updateSpecialTreatment(SpecialTreatment dummy, int id){
        repo.updateSpecialTreatment(dummy,id);
    }
    public int insertSpecialTreatmet(SpecialTreatment st){
        return repo.insertSpecialTreatment(st);
    }
}
