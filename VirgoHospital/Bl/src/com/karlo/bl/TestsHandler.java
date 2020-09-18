package com.karlo.bl;

import com.karlo.model.Patient;
import com.karlo.model.Test;
import java.util.List;

public class TestsHandler extends HandlerBase{
    public List<Test> getTestsForPatient(Patient p){
        return repo.getTestsForPatient(p);
    }
    public void insertTest(Test t){
        repo.insertTest(t);
    }
}
