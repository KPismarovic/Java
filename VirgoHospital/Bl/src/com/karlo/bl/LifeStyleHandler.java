package com.karlo.bl;

import com.karlo.model.LifeStyle;


public class LifeStyleHandler extends HandlerBase{
    public LifeStyle getLifeStyle(int id){
        return repo.getLifeStyle(id);
    }
    public void updateLifeStyle(LifeStyle dummy, int id){
        repo.updateLifeStyle(dummy,id);
    }
    public int insertLifeStyle(LifeStyle ls){
        return repo.insertLifestyle(ls);
    }
}
