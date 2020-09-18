package com.karlo.bl;

import com.karlo.model.NextOfKin;


public class NextOfKinHandler extends HandlerBase{
    public NextOfKin getNextOfKin(int id){
        return repo.getNextOfKin(id);
    }
    public void updateNextOfKin(NextOfKin dummy, int id){
        repo.updateNextOfKin(dummy,id);
    }
    public int insertNextOfKin(NextOfKin nok){
        return repo.insertNextOfKin(nok);
    }
}
