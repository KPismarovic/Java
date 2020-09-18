package com.karlo.bl;

import com.karlo.model.BasicComplaint;
import com.karlo.model.MedicalPerscription;


public class BasicComplaintsHandler extends HandlerBase {
    public BasicComplaint getBasicComplaint(int id){
        return repo.getBasicComplaint(id);
    }
    public void updateBasicComplaint(BasicComplaint dummy, int id){
        repo.updateBasicComplaint(dummy,id);
    }
    public int insertBasicComplaint(BasicComplaint bc){
        return repo.insertBasciComplaint(bc);
    }
    
}
