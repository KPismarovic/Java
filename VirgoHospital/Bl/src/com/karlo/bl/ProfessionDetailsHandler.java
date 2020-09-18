package com.karlo.bl;

import com.karlo.model.ProfessionDetail;


public class ProfessionDetailsHandler extends HandlerBase{
    public ProfessionDetail getProfessionDetail(int id){
        return repo.getProfessionDetails(id);
    }
    public void updateProfessionDetails(ProfessionDetail dummy, int id){
        repo.updateProfessionDetails(dummy,id);
    }
    public int insertProfessionDetails(ProfessionDetail pd){
        return repo.insertProfessionDetails(pd);
    }
}
