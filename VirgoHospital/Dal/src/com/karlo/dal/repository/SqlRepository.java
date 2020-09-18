package com.karlo.dal.repository;

import com.karlo.dal.datasource.DataSourceSingleton;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class SqlRepository  implements IRepository{
    
    
    private static final String INSERT_GENERALPHYSICIAN = "{ CALL insertGeneralPhysician(?,?,?,?) }";
    private static final String DELETE_GENERALPHYSICIAN = "{ CALL deleteGeneralPhysician(?) }";
    private static final String UPDATE_GENERALPHYSICIAN = "{ CALL updateGeneralPhysician(?,?,?,?) }";
    private static final String GET_GENERALPHYSICIAN = "{ CALL getGeneralPhysician(?) }";
    private static final String GET_GENERALPHYSICIANS = "{ CALL getGeneralPhysicians }";
    
    private static final String GET_PATIENTS_FOR_GENERALPHYSICIAN = "{ CALL getPatientsForGeneralPhysician(?) }";
    
    private static final String GET_PATIENTS = "{ CALL getPatients }";
    private static final String GET_PATIENT = "{CALL getPatient (?)}";
    private static final String GET_DETAILED_PATIENT = "{CALL getDetailedPatient(?)}";
    private static final String UPDATE_PATIENT = "{CALL updatePatient(?,?,?,?,?)}";
    private static final String FAST_REGISTRY_OF_PATIENT = "{ CALL fastRegistryOfPatient(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String COMPREHENSVE_REGISTRY_OF_PATIENT = "{ CALL comprehensiveRegistryOfPatient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_PATIENT = "{ CALL deletePatient(?)}";
    private static final String INSERT_TEST ="{CALL insertTest(?,?,?)}";
    private static final String INSERT_SPECIALIST= "{ CALL insertSpecialist(?,?,?,?,?) }";
    private static final String DELETE_SPECIALIST = "{ CALL deleteSpecialist(?) }";
    private static final String UPDATE_SPECIALIST = "{ CALL updateSpecialist(?,?,?,?) }";
    private static final String GET_SPECIALIST = "{ CALL getSpecialist(?) }";
    private static final String GET_SPECIALISTS = "{ CALL getSpecialists }";
    
    
    private static final String GET_SPECIALIZATIONS = "{ CALL getSpecializations()}";
    private static final String GET_SPECIALIZATION = "{ CALL getSpecialization(?)}";
    private static final String GET_SPECIALIZATION_FROM_SP = "{ CALL getSpecializationId(?,?,?)}";
    
    private static final String CREATE_APPOINTMENT ="{CALL createAppointment(?,?,?,?)}";
    private static final String GET_APPOINTMENTS = "{CALL getAppointments()}";
    private static final String GET_UPCOMING_APPOINTMENTS = "{CALL getUpcomingAppointments()}";
    private static final String DELETE_APPOINTMENT = "{CALL deleteAppointment(?)}";
    private static final String GET_PAYMENT = " { CALL getPayment(?)}";
    private static final String GET_APPOINTMENT ="{CALL getAppointment(?)}";
    
    private static final String GET_TREATMENT_FOR_APPOINTMENT ="{CALL getTreatmentForApp(?)}";
    private static final String GET_TREATMENT = "{ CALL getTreatment(?)}";
    
    
    private static final String GET_BASIC_COMPLAINTS = "{CALL getBasicComplaints(?)}";
    private static final String GET_CONTACT_DETAILS = "{CALL getContactDetails(?)}";
    private static final String GET_GENERAL_TREATMENT = "{CALL getGeneralTreatment(?)}";
    private static final String GET_IMPORTANT_MED_COM = "{CALL getImportantMedCom(?)}";
    private static final String GET_LIFESTYLE = "{CALL getLifeStyle(?)}";
    private static final String GET_MEDICAL_PERSCRIPTION = "{CALL getMedicalPerscription(?)}";
    private static final String GET_SPECIAL_TREATMENT = "{CALL getSpecialTreatment(?)}";
    private static final String GET_PERSONAL_DETAILS = "{CALL getPersonalDetails(?)}";
    private static final String GET_NEXT_OF_KIN = "{CALL getNextOfKin(?)}";
    private static final String GET_PERSON_ADDRESS = "{CALL getPersonAddress(?)}";
    private static final String GET_PROFESSION_DETAILS = "{CALL getProfessionDetails(?)}";
   
    private static final String UPDATE_BASIC_COMPLAINTS="{ CALL updateBasicComplaints(?,?,?,?)}";
    private static final String UPDATE_CONTACT_DETAILS ="{ CALL updateContactDetails(?,?,?,?,?,?,?)}";
    private static final String UPDATE_GENERAL_TREATMENT ="{ CALL updateGeneralTreatment(?,?)}";
    private static final String UPDATE_IMPORTANT_MED_COM ="{ CALL updateImportantMedicalComplaint(?,?,?,?,?,?,?,?,?,?,?,?)}";
    private static final String UPDATE_LIFESTYLE ="{ CALL updateLifestyle(?,?,?,?,?,?,?,?,?)}";
    private static final String UPDATE_MEDICAL_PERSCRIPTION ="{ CALL updateMedicalPerscription(?,?)}";
    private static final String UPDATE_NEXT_OF_KIN="{ CALL updateNextOfKin(?,?,?,?,?)}";
    private static final String UPDATE_PAYMENT="{ CALL updatePayment(?,?)}";
    private static final String ADD_TO_PAYMENT="{ CALL addToPayment(?,?)}";
    private static final String UPDATE_PERSON_ADDRESS="{ CALL updatePersonAddress(?,?,?,?,?,?,?)}";
    private static final String UPDATE_PERSONAL_DETAILS="{ CALL updatePersonalDetails(?,?,?,?,?,?)}";
    private static final String UPDATE_PROFESSION_DETAILS="{ CALL updateProfessionDetails(?,?,?)}";
    private static final String UPDATE_SPECIAL_TREATMENT="{ CALL updateSpecialTreatment(?,?)}";
    private static final String INSERT_SPECIALIZATION ="{ CALL insertSpecialization(?)}";
    private static final String UPDATE_TREATMENT ="{ CALL updateTreatment(?,?,?)}";
    private static final String UPDATE_SPECIALIZATION ="{ CALL updateSpecialization(?,?)}";
    private static final String INSERT_MEDICAL_PERSCRIPTION ="{CALL insertMedicalPerscription(?,?)}";
    private static final String INSERT_CONTACT_DETAILS ="{CALL insertContactDetail(?,?,?,?,?,?,?)}";
    private static final String INSERT_BASIC_COMPLAINTS ="{CALL insertBasciComplaint(?,?,?,?)}";
    private static final String INSERT_IMP_MED_COM ="{CALL insertImportantMedComp(?,?,?,?,?,?,?,?,?,?,?,?)}";
    private static final String INSERT_LIFESTYLE ="{CALL insertLifestyle(?,?,?,?,?,?,?,?,?)}";
    private static final String INSERT_NEXT_OF_KIN ="{CALL insertNextOfKin(?,?,?,?,?)}";
    private static final String INSERT_PAYMENT ="{CALL insertPayment(?,?)}";
    private static final String INSERT_PERSON_ADDRESS ="{CALL insertPersonAddress(?,?,?,?,?,?,?)}";
    private static final String INSERT_PERSONAL_DETAILS ="{CALL insertPersonalDetails(?,?,?,?,?,?)}";
    private static final String INSERT_PROFESSION_DETAILS ="{CALL insertProfessionDetails(?,?,?)}";
    private static final String INSERT_SPECIAL_TREATMENT ="{CALL insertSpecialTreatment(?,?)}";
    private static final String GET_APP_FOR_PATIENT ="{CALL getAppForPatient(?)}";
    private static final String GET_M_P_FOR_PATIENT ="{CALL getMedPerForPatient(?)}";
    private static final String GET_TESTS_FOR_PATIENT ="{CALL getTestsForPatient(?)}";
    
    
    // <editor-fold defaultstate="collapsed" desc="GeneralPhysician SQL">
    
    @Override
    public int insertGeneralPhysician(GeneralPhysician generalPhysician) {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_GENERALPHYSICIAN);
                ){
            stmt.setString(1, generalPhysician.getName());
            stmt.setString(2, generalPhysician.getSurname());
            stmt.setString(3, generalPhysician.getTitle());            
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateGeneralPhysician(GeneralPhysician dummy, int idGeneralPhysician) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_GENERALPHYSICIAN);
                ){
            stmt.setString(1, dummy.getName());
            stmt.setString(2, dummy.getSurname());
            stmt.setString(3, dummy.getTitle());            
            stmt.setInt(4, idGeneralPhysician);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGeneralPhysician(int idGeneralPhysician) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_GENERALPHYSICIAN);
                ){
            stmt.setInt(1, idGeneralPhysician);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public GeneralPhysician getGeneralPhysician(int idGeneralPhysician) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENERALPHYSICIAN);
                ){
            stmt.setInt(1, idGeneralPhysician);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return new GeneralPhysician(
                            rs.getInt("IDGeneralPhysician"),
                            rs.getString("FirstName"),
                            rs.getString("Surname"),
                            rs.getString("Title"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GeneralPhysician> getGeneralPhysicians() {
        List<GeneralPhysician> generalPhysicians = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENERALPHYSICIANS);
                ResultSet rs = stmt.executeQuery();
                ){            
                while(rs.next()){
                    generalPhysicians.add(new GeneralPhysician(
                            rs.getInt("IDGeneralPhysician"),
                            rs.getString("FirstName"),
                            rs.getString("Surname"),
                            rs.getString("Title")));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generalPhysicians;
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GetPatientsFor... SQL">
    @Override
    public List<Patient> getPatientsForGeneralPhysician(int idGeneralPhysician) {
        List<Patient> patients = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PATIENTS_FOR_GENERALPHYSICIAN);
                ){
            stmt.setInt(1, idGeneralPhysician);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    patients.add(new Patient(
                            rs.getInt("IDPatient"),
                            rs.getString("FirstName"),
                            rs.getString("Surname"),
                            rs.getString("Sex"),
                    rs.getString("DOB"),
                    rs.getTimestamp("DateOfCreation")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }
    
    @Override
    public List<Patient> getPatientsForSpecialist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Patient SQL">
    @Override
    public void updatePatient(Patient dummy, int idPatient) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PATIENT);
                ){
            stmt.setInt(1, idPatient);
            stmt.setString(2, dummy.getName());
            stmt.setString(3, dummy.getSurname());            
            stmt.setString(4, dummy.getSex());
            stmt.setString(5, dummy.getDob());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int idPatient) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PATIENT);
                ){
            stmt.setInt(1, idPatient);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatient(int idPatient) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PATIENT);
                ){
            stmt.setInt(1, idPatient);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id = rs.getInt("IDPatient");
                    String name = rs.getString("FirstName");
                    String lastname = rs.getString("Surname");
                    String sex = rs.getString("Sex");
                    String dob = rs.getString("DOB");
                    Timestamp doc = rs.getTimestamp("DateOfCreation");
                    return new Patient(id,name,lastname,sex,dob,doc);
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PATIENTS);
                ResultSet rs = stmt.executeQuery();
                ){            
                while(rs.next()){
                    patients.add(new Patient(
                            rs.getInt("IDPatient"),
                            rs.getString("FirstName"),
                            rs.getString("Surname"),
                            rs.getString("Sex"),
                    rs.getString("DOB"),
                    rs.getTimestamp("DateOfCreation")));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public int fastRegistyOfPatient(Patient p, BasicComplaint bc, ContactDetail cd, NextOfKin nok) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(FAST_REGISTRY_OF_PATIENT);
                ){
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getSurname());
            stmt.setString(3, p.getSex()); 
            stmt.setString(4, p.getDob());
            stmt.setString(5, bc.getStatementOfComplaint());
            stmt.setString(6, cd.getTelephoneHome());
            stmt.setString(7, cd.getMobile());            
            stmt.setString(8, bc.getHistoryOfPreviousTreatment());
            stmt.setString(9, bc.getPhysicianOrHospitalTreated());
            stmt.setString(10, nok.getName());            
            stmt.setString(11, nok.getMiddleName());
            stmt.setString(12, nok.getSurname());
            stmt.setString(13, nok.getRelationshipWithPatient());  
            stmt.registerOutParameter(14, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(14);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Specialist SQL">
    @Override
    public void deleteSpecialist(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_SPECIALIST);
                ){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int insertSpecialist(Specialist specialist) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_SPECIALIST);
                ){
            
            stmt.setString(1, specialist.getName());
            stmt.setString(2, specialist.getSurname());
            stmt.setString(3, specialist.getTitle());            
            stmt.setInt(4, specialist.getSpecialization().getId());            
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateSpecialist(Specialist dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_SPECIALIST);
                ){
            stmt.setString(1, dummy.getName());
            stmt.setString(2, dummy.getSurname());
            stmt.setString(3, dummy.getTitle());            
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSpecialist(Specialist specialist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Specialist getSpecialist(int idSpecialist) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIALIST);
                ){
            stmt.setInt(1, idSpecialist);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id = rs.getInt("IDSpecialist");
                    String name = rs.getString("FirstName");
                    String lastname = rs.getString("Surname");
                    String title = rs.getString("Title");
                    int spec = rs.getInt("SpecializationID");                    
                    return new Specialist(id,name,lastname,title,new Specialization(spec,getSpecialization(spec).getName()));
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Specialist> getSpecialists() {
        List<Specialist> spec = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIALISTS);
                ResultSet rs = stmt.executeQuery();
                ){            
                while(rs.next()){
                    spec.add(new Specialist(
                            rs.getInt("IDSpecialist"),
                            rs.getString("FirstName"),
                            rs.getString("Surname"),
                            rs.getString("Title"),
                    new Specialization(rs.getInt("SpecializationID"), rs.getString("NameOfSpecialization"))));                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spec;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Specializations SQL">

    @Override
    public List<Specialization> getSpecializations() {
        List<Specialization> spec = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIALIZATIONS);
                ResultSet rs = stmt.executeQuery();
                ){            
                while(rs.next()){
                    spec.add(new Specialization(
                            rs.getInt("IDSpecialization"),
                            rs.getString("NameOfSpecialization")));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spec;
    }

    @Override
    public Specialization getSpecialization(int idSpec) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIALIZATION);
                ){
            stmt.setInt(1, idSpec);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id = rs.getInt("IDSpecialization");
                    String name = rs.getString("NameOfSpecialization");
                    
                    return new Specialization(id,name);
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // </editor-fold>



    @Override
    public int createAppointment(GeneralPhysician gp, Patient p, Appointment a) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_APPOINTMENT);
                ){
            stmt.setInt(1, gp.getId());
            stmt.setInt(2, p.getId());
            stmt.setDate(3, a.getDate());            
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Specialization getSpecializationFromSpec(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIALIZATION_FROM_SP);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id2 = rs.getInt("SpecializationID");
                    String name = rs.getString("NameOfSpecialization");
                    return new Specialization(id2,name);
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Appointment> getAppointments() {
    List<Appointment> app = new ArrayList<>();
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
    CallableStatement stmt = con.prepareCall(GET_APPOINTMENTS);
    ResultSet rs = stmt.executeQuery();
    ){
    while(rs.next()){
        java.sql.Date d = rs.getDate("DayOfAppointment");
        d.setDate(d.getDate() + 2);
    app.add(new Appointment(
        rs.getInt("IDAppointment"),
        d,
            getPayment(rs.getInt("PaymentID"))))
            
    ;
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
    return app;
    }
    
     @Override
    public List<Appointment> getUpcomingAppointments() {
        List<Appointment> app = new ArrayList<>();
    DataSource dataSource = DataSourceSingleton.getInstance();
    try (Connection con = dataSource.getConnection();
    CallableStatement stmt = con.prepareCall(GET_UPCOMING_APPOINTMENTS);
    ResultSet rs = stmt.executeQuery();
    ){
    while(rs.next()){
        java.sql.Date d = rs.getDate("DayOfAppointment");
        d.setDate(d.getDate() + 2);
    app.add(new Appointment(
            
        rs.getInt("IDAppointment"),
            d,
            getPayment(rs.getInt("PaymentID"))
            ))
    ;
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
    return app;
    }

    @Override
    public Payment getPayment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PAYMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id1 = rs.getInt("IDPayment");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    return new Payment(id,amount);
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAppointment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_APPOINTMENT);
                ){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @Override
    public Treatment getTreatmentForApp(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TREATMENT_FOR_APPOINTMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idT = rs.getInt("IDTreatment");
                    Patient a = getDetailedPatient(rs.getInt("IDPatient"));
                    SpecialTreatment b = getSpecialTreatment(rs.getInt("SpecialTreatmentID"));
                    GeneralTreatment c = getGeneralTreatment(rs.getInt("GeneralTreatmentID"));
                    Appointment d = getAppointment(rs.getInt("AppointmentID"));
                    MedicalPerscription e = getMedicalPerscription(rs.getInt("MedicalPerscriptionID"));
                    String f = rs.getString("Diagnosis");
                    return new Treatment(idT, a, b, c, d, e, f);
                    
                    
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BasicComplaint getBasicComplaint(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_BASIC_COMPLAINTS);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idBC = rs.getInt("IDBasicComplaints");
                    String soc = rs.getString("StatementOfComplaint");
                    String h = rs.getString("HistoryOfPreviousTreatment");
                    String p = rs.getString("PhysicianOrHospitalTreated");
                    return new BasicComplaint(idBC,soc,h,p);        
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ContactDetail getContactDetail(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CONTACT_DETAILS);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDContactDetails");
                    String t1 = rs.getString("TelephoneWork");
                    String t2 = rs.getString("TelephoneHome");
                    String m = rs.getString("Mobile");
                    String p = rs.getString("Pager");
                    String fax = rs.getString("Fax");
                    String email = rs.getString("Email");
                    PersonAddress pa = getPersonAddress(rs.getInt("PresentAddressID"));
                    PersonAddress pa2 = getPersonAddress(rs.getInt("PermanentAddressID"));
                    PersonalDetails pd = getPersonalDetails(rs.getInt("PersonalDetailsID"));
                    return new ContactDetail(idCD,t1,t2,m,p,fax,email,pa,pa2,pd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneralTreatment getGeneralTreatment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENERAL_TREATMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDGeneralTreatment");
                    String tot = rs.getString("TypeOfTreatment");
                    GeneralPhysician gp = getGeneralPhysician(rs.getInt("GeneralPhysicianID"));
                    return new GeneralTreatment(idCD, tot, gp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ImportantMedicalComplaint getImportantMedicalComplaint(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_IMPORTANT_MED_COM);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDGeneralTreatment");
                    String a = rs.getString("Diabetic");
                    String b = rs.getString("Hypertensive");
                    String c = rs.getString("CardiacCondition");
                    String d = rs.getString("RespiratoryCondition");
                    String e = rs.getString("DigestiveCondition");
                    String f = rs.getString("OrthopedicCondition");
                    String g = rs.getString("MuscularCondition");
                    String h = rs.getString("NeurologicalCondition");
                    String i = rs.getString("KnownAllergies");
                    String j = rs.getString("AdverseReactionToDrugs");
                    String k = rs.getString("HistoryOfMajorSurgeries");
                    return new ImportantMedicalComplaint(idCD, a, b, c, d, e, f, g, h, i, j,k);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LifeStyle getLifeStyle(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_LIFESTYLE);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDLifestyle");
                    String a = rs.getString("Vegetarian");
                    String b = rs.getString("Smoker");
                    String c = rs.getString("ConsumeAlcoholicBeverage");
                    String d = rs.getString("Stimulans");
                    String e = rs.getString("ConsumptionOfCoffeePerDay");
                    String f = rs.getString("ConsumptionOfSoftDrinksPerDay");
                    String g = rs.getString("RegularityOfMeals");
                    String h = rs.getString("EatingHomeOrOutside");
                    
                    return new LifeStyle(idCD,a, b, c, d, e, f, g, h);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MedicalPerscription getMedicalPerscription(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MEDICAL_PERSCRIPTION);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDMedicalPerscription");
                    String a = rs.getString("Name");
                    
                    return new MedicalPerscription(idCD, a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NextOfKin getNextOfKin(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_NEXT_OF_KIN);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDNextOfKin");
                    String a = rs.getString("FirstName");
                    String b = rs.getString("MiddleName");
                    String c = rs.getString("Surname");
                    String d = rs.getString("RelationshipWithPatient");
                    ContactDetail c1 = getContactDetail(rs.getInt("ContactDetailsID"));
                    PersonAddress p2 = getPersonAddress(rs.getInt("ContactAddressID"));
                    
                    return new NextOfKin(idCD,a,c,b,d,c1,p2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PersonalDetails getPersonalDetails(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PERSONAL_DETAILS);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDNextOfKin");
                    String a = rs.getString("MaritalStatus");
                    String b = rs.getString("NoOfDependents");
                    String height = rs.getString("HeightInCm");
                    String c = rs.getString("HeightInCm");
                    String d = rs.getString("BloodType");
                    
                    
                    return new PersonalDetails(idCD, a, b,height, c,d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SpecialTreatment getSpecialTreatment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_SPECIAL_TREATMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDSpecialTreatment");
                    String a = rs.getString("TypeOfTreatment");
                    Specialist spec = getSpecialist(rs.getInt("SpecialistID"));
                    
                    
                    
                    return new SpecialTreatment(idCD, a, spec);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Treatment getTreatment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TREATMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDNextOfKin");
                    Patient a = getPatient(rs.getInt("PatientID"));
                    SpecialTreatment b = getSpecialTreatment(rs.getInt("SpecialTreatment"));
                    GeneralTreatment c = getGeneralTreatment(rs.getInt("GeneralTreatmentID"));
                    Appointment d = getAppointment(rs.getInt("AppointmentID"));
                    MedicalPerscription e = getMedicalPerscription(rs.getInt("MedicalPerscriptionID"));
                    String f = rs.getString("Diagnosis");
                    
                    return new Treatment(idCD, a, b, c, d, e, f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProfessionDetail getProfessionDetails(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PROFESSION_DETAILS);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDProfessionDetails");
                    String a = rs.getString("Occupation");
                    BigDecimal b = rs.getBigDecimal("GrossAnnualIncome");
                    
                    return new ProfessionDetail(idCD, a, b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient getDetailedPatient(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_DETAILED_PATIENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int id1 = rs.getInt("IDPatient");
                    String name = rs.getString("FirstName");
                    String lastname = rs.getString("Surname");
                    String sex = rs.getString("Sex");
                    String dob = rs.getString("DOB");
                    Timestamp doc = rs.getTimestamp("DateOfCreation");
                    ContactDetail cd = null;
                    ProfessionDetail pd = null;
                    BasicComplaint bc = null;
                    ImportantMedicalComplaint imc = null;
                    LifeStyle ls = null;
                    NextOfKin nok = null;
                    try {
                        cd = getContactDetail(rs.getInt("ContactDetailsID"));
                        pd = getProfessionDetails(rs.getInt("ProfessionDetailsID"));
                        bc = getBasicComplaint(rs.getInt("BasicComplaintsID"));
                        imc = getImportantMedicalComplaint(rs.getInt("ImportantMedicalComplaintsID"));
                        ls = getLifeStyle(rs.getInt("LifeStyleID"));
                        nok = getNextOfKin(rs.getInt("NextOfKinID"));
                    } catch (SQLException sQLException) {
                    }
                    return new Patient(id,name,lastname,doc,dob,sex,cd,pd,bc,imc,ls,nok);
                            
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public PersonAddress getPersonAddress(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PERSON_ADDRESS);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDPersonAddress");
                    String a = rs.getString("DoorNumber");
                    String b = rs.getString("Street");
                    String c = rs.getString("Area");
                    String e = rs.getString("City");
                    String f = rs.getString("State");
                    String g = rs.getString("PinCode");
                   
                    
                    return new PersonAddress(idCD, a, b, c, e, f,g);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Appointment getAppointment(int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_APPOINTMENT);
                ){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    int idCD = rs.getInt("IDAppointment");
                    Date a = rs.getDate("DayOfAppointment");
                    Payment b = getPayment(rs.getInt("PaymentID"));
                    
                    
                    return new Appointment(idCD,a,b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBasicComplaint(BasicComplaint dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_BASIC_COMPLAINTS);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getStatementOfComplaint());
            stmt.setString(3, dummy.getHistoryOfPreviousTreatment());            
            stmt.setString(4, dummy.getPhysicianOrHospitalTreated());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContactDetails(ContactDetail dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_CONTACT_DETAILS);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getTelephoneWork());
            stmt.setString(3, dummy.getTelephoneHome());            
            stmt.setString(4, dummy.getMobile());
            stmt.setString(5, dummy.getPager());
            stmt.setString(6, dummy.getFax());
            stmt.setString(7, dummy.getEmail());
            try {
                updatePersonAddress(dummy.getPresentAddress(), dummy.getPresentAddress().getId());
                updatePersonAddress(dummy.getPermanentAddress(), dummy.getPermanentAddress().getId());
                updatePersonalDetails(dummy.getPersonalDetails(), dummy.getPersonalDetails().getId());
            } catch (Exception e) {
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGeneralTreatment(GeneralTreatment dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_GENERAL_TREATMENT);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getTot());
            try {
                updateGeneralPhysician(dummy.getGp(), dummy.getGp().getId());
            } catch (Exception e) {
            }
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateImportantMedicalComplaint(ImportantMedicalComplaint dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_IMPORTANT_MED_COM);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getDiabetic());
            stmt.setString(3, dummy.getHypertensive());            
            stmt.setString(4, dummy.getCardiacCondition());
            stmt.setString(5, dummy.getRespiratoryCondition());
            stmt.setString(6, dummy.getDigestiveCondition());
            stmt.setString(7, dummy.getOrthopedicCondition());
            stmt.setString(8, dummy.getMuscularCondition());
            stmt.setString(9, dummy.getNeurologicalCondition());            
            stmt.setString(10, dummy.getKnownAllergies());
            stmt.setString(11, dummy.getAdverseReactionToDrugs());
            stmt.setString(12, dummy.getHistoryOfMajorSurgeries());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLifeStyle(LifeStyle dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_LIFESTYLE);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getVegetarian());
            stmt.setString(3, dummy.getSmoker());            
            stmt.setString(4, dummy.getAlcohol());
            stmt.setString(5, dummy.getStimulans());
            stmt.setString(6, dummy.getConsupmtionOfCoffe());
            stmt.setString(7, dummy.getConsumptionOfSoftDrinks());
            stmt.setString(8, dummy.getRegularityOfMeals());
            stmt.setString(9, dummy.getEatingHabits());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicalPerscription(MedicalPerscription dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MEDICAL_PERSCRIPTION);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getName());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNextOfKin(NextOfKin dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_NEXT_OF_KIN);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getName());
            stmt.setString(3, dummy.getMiddleName());            
            stmt.setString(4, dummy.getSurname());
            stmt.setString(5, dummy.getRelationshipWithPatient());
            try {
                updateContactDetails(dummy.getContactDetail(), dummy.getContactDetail().getId());
                updatePersonAddress(dummy.getContactAddress(), dummy.getContactAddress().getId());
            } catch (Exception e) {
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PAYMENT);
                ){
            stmt.setInt(1, id);
            stmt.setBigDecimal(2, dummy.getCost());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToPayment(BigDecimal amount, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_TO_PAYMENT);
                ){
            stmt.setInt(1, id);
            stmt.setBigDecimal(2, amount);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePersonAddress(PersonAddress dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON_ADDRESS);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getDoorNumber());
            stmt.setString(3, dummy.getStreet());            
            stmt.setString(4, dummy.getArea());
            stmt.setString(5, dummy.getCity());
            stmt.setString(6, dummy.getState());
            stmt.setString(7, dummy.getPincode());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePersonalDetails(PersonalDetails dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSONAL_DETAILS);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getMaritalStatus());
            stmt.setString(3, dummy.getNoOfDependents());            
            stmt.setString(4, dummy.getHeight());
            stmt.setString(5, dummy.getWeight());
            stmt.setString(6, dummy.getBloodType());
            
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfessionDetails(ProfessionDetail dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PROFESSION_DETAILS);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getOccupation());
            stmt.setBigDecimal(3, dummy.getIncome());            
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSpecialTreatment(SpecialTreatment dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_SPECIAL_TREATMENT);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getTot());
            try {
                updateSpecialist(dummy.getSpec(), dummy.getSpec().getId());
            } catch (Exception e) {
            }
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSpecialization(Specialization spec) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_SPECIALIZATION);
                ){
            stmt.setInt(1, spec.getId());
            stmt.setString(2, spec.getName());
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTreatment(Treatment dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_TREATMENT);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getDiagnosis());
            stmt.setInt(3, dummy.getMp().getId());
            try{
                updatePatient(dummy.getPatient(), dummy.getPatient().getId());
            updateSpecialTreatment(dummy.getSt(), dummy.getSt().getId());
            updateGeneralTreatment(dummy.getGt(), dummy.getGt().getId());
            updateMedicalPerscription(dummy.getMp(), id);
            }
            catch(Exception ef){}
                        updateMedicalPerscription(dummy.getMp(), id);
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSpecialization(Specialization dummy, int id) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_SPECIALIZATION);
                ){
            stmt.setInt(1, id);
            stmt.setString(2, dummy.getName());
            
            
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertMedicalPerscription(MedicalPerscription med) {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_MEDICAL_PERSCRIPTION);
                ){
            stmt.setString(1, med.getName());
                      
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertBasciComplaint(BasicComplaint bc) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_BASIC_COMPLAINTS);
                ){
            stmt.setString(1, bc.getStatementOfComplaint());
            stmt.setString(2, bc.getHistoryOfPreviousTreatment());
            stmt.setString(3, bc.getPhysicianOrHospitalTreated());            
            stmt.registerOutParameter(4, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertContactDetail(ContactDetail cd) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_CONTACT_DETAILS);
                ){
            stmt.setString(1, cd.getTelephoneWork());
            stmt.setString(2, cd.getTelephoneHome());
            stmt.setString(3, cd.getMobile());            
            stmt.setString(4, cd.getPager());
            stmt.setString(5, cd.getFax());
            stmt.setString(6, cd.getEmail());            
            stmt.registerOutParameter(7, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertImportantMedComp(ImportantMedicalComplaint imc) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_IMP_MED_COM);
                ){
            stmt.setString(1, imc.getDiabetic());
            stmt.setString(2, imc.getHypertensive());
            stmt.setString(3, imc.getCardiacCondition());            
            stmt.setString(4, imc.getRespiratoryCondition());
            stmt.setString(5, imc.getDigestiveCondition());
            stmt.setString(6, imc.getOrthopedicCondition());            
            stmt.setString(7, imc.getMuscularCondition());
            stmt.setString(8, imc.getNeurologicalCondition());
            stmt.setString(9, imc.getKnownAllergies());            
            stmt.setString(10, imc.getAdverseReactionToDrugs());
            stmt.setString(11, imc.getHistoryOfMajorSurgeries());
                      
            stmt.registerOutParameter(12, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertLifestyle(LifeStyle ls) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_LIFESTYLE);
                ){
            stmt.setString(1, ls.getVegetarian());
            stmt.setString(2, ls.getSmoker());
            stmt.setString(3, ls.getAlcohol());            
            stmt.setString(4, ls.getStimulans());
            stmt.setString(5, ls.getConsupmtionOfCoffe());
            stmt.setString(6, ls.getConsumptionOfSoftDrinks());
            stmt.setString(7, ls.getRegularityOfMeals());
            stmt.setString(8, ls.getEatingHabits());
                      
            stmt.registerOutParameter(9, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertNextOfKin(NextOfKin nok) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_NEXT_OF_KIN);
                ){
            stmt.setString(1, nok.getName());
            stmt.setString(2, nok.getMiddleName());
            stmt.setString(3, nok.getSurname());            
            stmt.setString(5, nok.getRelationshipWithPatient());            
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertPayment(Payment p) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_PAYMENT);
                ){
            stmt.setBigDecimal(1, p.getCost());
                       
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertPersonAddress(PersonAddress pa) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_PERSON_ADDRESS);
                ){
            stmt.setString(1, pa.getDoorNumber());
            stmt.setString(2, pa.getStreet());           
            stmt.setString(3, pa.getArea());
            stmt.setString(4, pa.getCity());
            stmt.setString(5, pa.getState());
            stmt.setString(6, pa.getPincode());
            stmt.registerOutParameter(7, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertPersonalDetails(PersonalDetails pd) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_PERSONAL_DETAILS);
                ){
            stmt.setString(1, pd.getMaritalStatus());
            stmt.setString(2, pd.getNoOfDependents());
            stmt.setString(3, pd.getHeight());            
            stmt.setString(4, pd.getWeight());            
            stmt.setString(5, pd.getBloodType());            
            stmt.registerOutParameter(6, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertProfessionDetails(ProfessionDetail pd) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_PROFESSION_DETAILS);
                ){
            stmt.setString(1, pd.getOccupation());
            stmt.setBigDecimal(2, pd.getIncome());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insertSpecialTreatment(SpecialTreatment st) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_SPECIAL_TREATMENT);
                ){
            stmt.setString(1, st.getTot());
                     
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Appointment> getAppForPatient(Patient p) {
        List<Appointment> app = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_APP_FOR_PATIENT);
                ){
            stmt.setInt(1, p.getId());
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    app.add(new Appointment(
                            rs.getInt("IDAppointment"),
                            rs.getDate("DayOfAppointment"),
                            getPayment(rs.getInt("PaymentID"))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app;
    }

    @Override
    public List<Test> getTestsForPatient(Patient p) {
        List<Test> app = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TESTS_FOR_PATIENT);
                ){
            stmt.setInt(1,p.getId());
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    app.add(new Test(
                            rs.getInt("IDTest"),
                            rs.getString("Name"),
                            rs.getString("Result"),
                            getPatient(rs.getInt("PatientID"))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app;
    }

    @Override
    public List<MedicalPerscription> getMedicalPerscriptionsForPatient(Patient p) {
        List<MedicalPerscription> app = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_M_P_FOR_PATIENT);
                ){
            stmt.setInt(1, p.getId());
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    app.add(new MedicalPerscription(
                            rs.getInt("IDMedicalPerscription"),
                            rs.getString("Name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app;
    }

    @Override
    public void insertTest(Test t) {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(INSERT_TEST);
                ){
            stmt.setString(1, t.getName());
            stmt.setString(2, t.getResult());
            stmt.setInt(3, t.getPatient().getId());
                     
            
            stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public int comprehensiveRegistryOfPatient(Patient p, ContactDetail cd, NextOfKin nok, BasicComplaint bc, ProfessionDetail prd, LifeStyle ls, ImportantMedicalComplaint imc, PersonalDetails ped, ContactDetail cdnok, PersonAddress pa, PersonAddress pa2, PersonAddress panok) {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(COMPREHENSVE_REGISTRY_OF_PATIENT);
                ){
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getSurname());
            stmt.setString(3, p.getSex()); 
            stmt.setString(4, p.getDob());
            stmt.setString(5, bc.getStatementOfComplaint());
            stmt.setString(7, cd.getTelephoneHome());
            stmt.setString(6, cd.getTelephoneWork());  
            stmt.setString(8, cd.getMobile());
            stmt.setString(9, cd.getPager());
            stmt.setString(10, cd.getFax());
            stmt.setString(11, cd.getEmail());
            stmt.setString(12, bc.getHistoryOfPreviousTreatment());
            stmt.setString(13, bc.getPhysicianOrHospitalTreated());
            stmt.setString(14, nok.getName());            
            stmt.setString(15, nok.getMiddleName());
            stmt.setString(16, nok.getSurname());
            stmt.setString(17, nok.getRelationshipWithPatient());
            stmt.setString(18, prd.getOccupation());
            stmt.setBigDecimal(19, prd.getIncome());
            stmt.setString(20, ls.getVegetarian());
            stmt.setString(21, ls.getSmoker());
            stmt.setString(22, ls.getAlcohol());
            stmt.setString(23, ls.getStimulans());
            stmt.setString(24, ls.getConsupmtionOfCoffe());
            stmt.setString(25, ls.getConsumptionOfSoftDrinks());
            stmt.setString(26, ls.getRegularityOfMeals());
            stmt.setString(27, ls.getEatingHabits());
            stmt.setString(28, imc.getDiabetic());
            stmt.setString(29, imc.getHypertensive());
            stmt.setString(30, imc.getCardiacCondition());
            stmt.setString(31, imc.getRespiratoryCondition());
            stmt.setString(32, imc.getDigestiveCondition());
            stmt.setString(33, imc.getOrthopedicCondition());
            stmt.setString(34, imc.getMuscularCondition());
            stmt.setString(35, imc.getNeurologicalCondition());
            stmt.setString(36, imc.getKnownAllergies());
            stmt.setString(37, imc.getAdverseReactionToDrugs());
            stmt.setString(38, imc.getHistoryOfMajorSurgeries());
            stmt.setString(39, ped.getMaritalStatus());
            stmt.setString(40, ped.getNoOfDependents());
            stmt.setString(41, ped.getHeight());
            stmt.setString(42, ped.getWeight());
            stmt.setString(43, ped.getBloodType());
            stmt.setString(44, cdnok.getTelephoneHome());
            stmt.setString(45, cdnok.getTelephoneWork());
            stmt.setString(46, cdnok.getMobile());
            stmt.setString(47, cdnok.getPager());
            stmt.setString(48, cdnok.getFax());
            stmt.setString(49, cdnok.getEmail());
            stmt.setString(50, pa.getDoorNumber());
            stmt.setString(51, pa.getStreet());
            stmt.setString(52, pa.getArea());
            stmt.setString(53, pa.getCity());
            stmt.setString(54, pa.getState());
            stmt.setString(55, pa.getPincode());
            stmt.setString(56, pa2.getDoorNumber());
            stmt.setString(57, pa2.getStreet());
            stmt.setString(58, pa2.getArea());
            stmt.setString(59, pa2.getCity());
            stmt.setString(60, pa2.getState());
            stmt.setString(61, pa2.getPincode());
            stmt.setString(62, panok.getDoorNumber());
            stmt.setString(63, panok.getStreet());
            stmt.setString(64, panok.getArea());
            stmt.setString(65, panok.getCity());
            stmt.setString(66, panok.getState());
            stmt.setString(67, panok.getPincode());
            stmt.registerOutParameter(68, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(68);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getContactDetailsForPatient(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  

    
    
}
