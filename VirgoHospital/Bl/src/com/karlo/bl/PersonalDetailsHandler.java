package com.karlo.bl;

import com.karlo.model.PersonalDetails;


public class PersonalDetailsHandler extends HandlerBase{
    public PersonalDetails getPersonalDetails(int id){
        return repo.getPersonalDetails(id);
    }
    public void updatePersonalDetails(PersonalDetails dummy, int id){
        repo.updatePersonalDetails(dummy,id);
    }
    public int insertPersonalDetails(PersonalDetails pd){
        return repo.insertPersonalDetails(pd);
        
    }
}
