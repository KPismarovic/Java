package com.karlo.bl;

import com.karlo.model.Payment;
import java.math.BigDecimal;


public class PaymentsHandler extends HandlerBase {
    public Payment getPayment(int id){
        return repo.getPayment(id);
    }
    public void updatePayment(Payment dummy,int id){
        repo.updatePayment(dummy,id);
    }
    public void addToPayment(BigDecimal amount,int id){
        repo.addToPayment(amount,id);
    }
    public int insertPayment(Payment p){
        return repo.insertPayment(p);
    }
}
