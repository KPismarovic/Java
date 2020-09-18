
package com.karlo.bl;
import com.karlo.model.Appointment;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.Patient;
import com.karlo.model.Treatment;
import java.util.List;


public class AppointmentsHandler extends HandlerBase {
    public int createAppointment(GeneralPhysician gp, Patient p, Appointment a){
    return repo.createAppointment(gp,p,a);
    }
    public List<Appointment> getAppointments(){
    return repo.getAppointments();
    }

    public void deleteAppointment(int id) {
        repo.deleteAppointment(id);
    }

    public List<Appointment> getUpcomingAppointments() {
        return repo.getUpcomingAppointments();
    }
    public Treatment getTreatmentForApp(int id){
        return repo.getTreatmentForApp(id);
    }
    public Appointment getAppointment(int id){
        return repo.getAppointment(id);
    }
    public List<Appointment> getAppForPatient(Patient p){
        return repo.getAppForPatient(p);
    }
}
