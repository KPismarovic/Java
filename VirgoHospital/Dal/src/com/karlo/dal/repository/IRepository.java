package com.karlo.dal.repository;

import com.karlo.model.Appointment;
import com.karlo.model.BasicComplaint;
import com.karlo.model.ContactDetail;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.GeneralTreatment;
import com.karlo.model.ImportantMedicalComplaint;
import com.karlo.model.LifeStyle;
import com.karlo.model.MedicalPerscription;
import com.karlo.model.NextOfKin;
import com.karlo.model.Patient;
import com.karlo.model.Payment;
import com.karlo.model.PersonAddress;
import com.karlo.model.PersonalDetails;
import com.karlo.model.ProfessionDetail;
import com.karlo.model.SpecialTreatment;
import com.karlo.model.Specialist;
import com.karlo.model.Specialization;
import com.karlo.model.Test;
import com.karlo.model.Treatment;
import java.math.BigDecimal;

import java.util.List;

public interface IRepository {
    // <editor-fold defaultstate="collapsed" desc="GeneralPhysician IRepo">
    int insertGeneralPhysician(GeneralPhysician generalPhysician);
    
    void updateGeneralPhysician(GeneralPhysician dummy, int idGeneralPhysician);
    
    void deleteGeneralPhysician(int idGeneralPhysician);
    
    GeneralPhysician getGeneralPhysician(int idGeneralPhysician);
    
    List<GeneralPhysician> getGeneralPhysicians();
    
    List<Patient> getPatientsForGeneralPhysician(int idGeneralPhysician);
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Specialist IRepo">
    int insertSpecialist(Specialist specialist);
    
    void updateSpecialist(Specialist dummy, int id);
    
    void deleteSpecialist(Specialist specialist);
    
    Specialist getSpecialist(int idSpecialist);
    
    List<Specialist> getSpecialists();
    
    Specialization getSpecializationFromSpec(int id);
    
    List<Patient> getPatientsForSpecialist();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Patient IRepo">
    void updatePatient(Patient dummy, int idPatient);
    
    void deletePatient(int idPatient);
    
    Patient getPatient(int idPatient);
    
    List<Patient> getPatients();
    
    public int fastRegistyOfPatient(Patient p, BasicComplaint bc, ContactDetail cd, NextOfKin nok);
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Specialization IRepo">
    public List<Specialization> getSpecializations();

    public Specialization getSpecialization(int idSpec);
    
    public void deleteSpecialist(int id);
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Appointments IRepo">
    
    List<Appointment> getAppointments();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Appointments IRepo">
    
    Payment getPayment(int id);
    
    // </editor-fold>
    

    public int createAppointment(GeneralPhysician gp, Patient p, Appointment a);

    public void deleteAppointment(int id);

    public List<Appointment> getUpcomingAppointments();

    public Treatment getTreatmentForApp(int id);

    public BasicComplaint getBasicComplaint(int id);

    public ContactDetail getContactDetail(int id);

    public GeneralTreatment getGeneralTreatment(int id);

    public ImportantMedicalComplaint getImportantMedicalComplaint(int id);

    public LifeStyle getLifeStyle(int id);

    public MedicalPerscription getMedicalPerscription(int id);

    public NextOfKin getNextOfKin(int id);

    public PersonalDetails getPersonalDetails(int id);

    public SpecialTreatment getSpecialTreatment(int id);

    public Treatment getTreatment(int id);

    public ProfessionDetail getProfessionDetails(int id);

    public Patient getDetailedPatient(int id);

    public PersonAddress getPersonAddress(int id);

    public Appointment getAppointment(int id);

    public void updateBasicComplaint(BasicComplaint dummy, int id);

    public void updateContactDetails(ContactDetail dummy, int id);

    public void updateGeneralTreatment(GeneralTreatment dummy, int id);

    public void updateImportantMedicalComplaint(ImportantMedicalComplaint dummy, int id);

    public void updateLifeStyle(LifeStyle dummy, int id);

    public void updateMedicalPerscription(MedicalPerscription dummy, int id);

    public void updateNextOfKin(NextOfKin dummy, int id);

    public void updatePayment(Payment dummy, int id);

    public void addToPayment(BigDecimal amount, int id);

    public void updatePersonAddress(PersonAddress dummy, int id);

    public void updatePersonalDetails(PersonalDetails dummy, int id);

    public void updateProfessionDetails(ProfessionDetail dummy, int id);

    public void updateSpecialTreatment(SpecialTreatment dummy, int id);

    public void insertSpecialization(Specialization spec);

    public void updateTreatment(Treatment dummy, int id);

    public void updateSpecialization(Specialization dummy, int id);

    public int insertMedicalPerscription(MedicalPerscription med);

    public int insertBasciComplaint(BasicComplaint bc);

    public int insertContactDetail(ContactDetail cd);

    

    public int insertImportantMedComp(ImportantMedicalComplaint imc);

    public int insertLifestyle(LifeStyle ls);

    public int insertNextOfKin(NextOfKin nok);

    public int insertPayment(Payment p);

    public int insertPersonAddress(PersonAddress pa);

    public int insertPersonalDetails(PersonalDetails pd);

    public int insertProfessionDetails(ProfessionDetail pd);

    public int insertSpecialTreatment(SpecialTreatment st);

    public List<Appointment> getAppForPatient(Patient p);

    public List<Test> getTestsForPatient(Patient p);

    public List<MedicalPerscription> getMedicalPerscriptionsForPatient(Patient p);

    public void insertTest(Test t);

    public int comprehensiveRegistryOfPatient(Patient p, ContactDetail cd, NextOfKin nok, BasicComplaint bc2, ProfessionDetail prd, LifeStyle ls, ImportantMedicalComplaint imc, PersonalDetails ped, ContactDetail cdnok, PersonAddress pa, PersonAddress pa2, PersonAddress panok);

    public int getContactDetailsForPatient(int id);

    

   
}
