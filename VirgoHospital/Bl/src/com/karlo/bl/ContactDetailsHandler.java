package com.karlo.bl;

import com.karlo.model.ContactDetail;

public class ContactDetailsHandler extends HandlerBase {
    public ContactDetail getContactDetail(int id){
        return repo.getContactDetail(id);
        
    }
    public void updateContactDetails(ContactDetail dummy, int id){
        repo.updateContactDetails(dummy,id);
    }
    public int insertContactDetails(ContactDetail cd){
        return repo.insertContactDetail(cd);
    }
 
}
