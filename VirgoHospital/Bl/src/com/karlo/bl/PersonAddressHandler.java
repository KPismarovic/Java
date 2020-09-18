package com.karlo.bl;

import com.karlo.model.PersonAddress;


public class PersonAddressHandler extends HandlerBase {
    public PersonAddress getPersonAddress(int id){
        return repo.getPersonAddress(id);
    }
    public void updatePersonAddress(PersonAddress dummy,int id){
        repo.updatePersonAddress(dummy,id);
}
    public int insertPersonAddress(PersonAddress pa){
        return repo.insertPersonAddress(pa);
    }
}

