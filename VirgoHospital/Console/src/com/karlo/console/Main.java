package com.karlo.console;

import com.karlo.bl.GeneralPhysiciansHandler;
import com.karlo.bl.PatientsHandler;
import com.karlo.bl.SpecialistsHandler;
import com.karlo.bl.SpecializationsHandler;
import com.karlo.model.BasicComplaint;
import com.karlo.model.ContactDetail;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.NextOfKin;
import com.karlo.model.Patient;
import com.karlo.model.Specialist;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    
    private static final GeneralPhysiciansHandler GENERALPHYSICIANS_HANDLER = new GeneralPhysiciansHandler();
    private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
    private static final SpecializationsHandler SPECIALIZATIONS_HANDLER = new SpecializationsHandler();
    private static final SpecialistsHandler SPECIALISTS_HANDLER = new SpecialistsHandler();
    
    public static void start() throws IOException {
        mainMenu();
        
    }

    private static void mainMenu() throws IOException {
        //performTesting();
        
        try (Scanner sc = new Scanner(System.in)){
            int option = ChooseAction(sc);
            
            switch (option) {
                case 1:
                    displayPatients();
                    break;
                case 2:
                    fastRegistrationOfPatient(sc);
                    break;
                case 3:
                    displayGeneralPhysicians();
                    break;
                case 4:
                    insertGeneralyPhysicist(sc);
                    break;
                case 5:
                    break;
                case 6:
                    insertSpecialist(sc);
                    break;
                case 7:
                    break;
                //displayGeneralPhysicians();
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int ChooseAction(Scanner sc) throws IOException {
        int s = -1;
        System.out.println("1. View Patients\n" +
                "2. Fast Register Of New Patient\n" +
                "3. View General Physicians\n" +
                "4. Create new General Physician\n" +
                "5. View Specialists\n" +
                "6. Create New Specialist\n" +
                "7. View Appointments\n" +
                "8. View Medical Perscriptions\n" +
                "9. Exit");
         System.out.print("Option: ");
         
            s = sc.nextInt();
            sc.nextLine();
        
            
        
        return s;
    }

    private static void performTesting() {
        int id = GENERALPHYSICIANS_HANDLER.insertGeneralPhysician(new GeneralPhysician("GeneralPhysician", "Strangelove", "He"));
        System.out.println(id);
        
        GeneralPhysician generalPhysiciansHandler = GENERALPHYSICIANS_HANDLER.getGeneralPhysician(id);
        System.out.println(generalPhysiciansHandler);
        
        GeneralPhysician dummy = new GeneralPhysician("Update name", "Update surname", "tit");
        GENERALPHYSICIANS_HANDLER.updateGeneralPhysician(dummy, id);        
        generalPhysiciansHandler = GENERALPHYSICIANS_HANDLER.getGeneralPhysician(id);        
        System.out.println(generalPhysiciansHandler);
        
        GENERALPHYSICIANS_HANDLER.deleteGeneralPhysician(id);
        generalPhysiciansHandler = GENERALPHYSICIANS_HANDLER.getGeneralPhysician(id);
        System.out.println(generalPhysiciansHandler);
        
        
        
        List<Patient> patients = PATIENTS_HANDLER.getPatientsForGeneralPhysician(1);
        System.out.println(patients);
    }

    private static void displayGeneralPhysicians() throws IOException {
        
        List<GeneralPhysician> generalPhysiciansHandlers = GENERALPHYSICIANS_HANDLER.getGeneralPhysicians();
        System.out.println(generalPhysiciansHandlers.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
    }


    private static void fastRegistrationOfPatient(Scanner sc) {
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Surname: ");
        String surname = sc.nextLine();
        System.out.println("Sex(F/M): ");
        String sex = sc.nextLine();
        System.out.println("Date Of Birth: ");
        String dob = sc.nextLine();
        System.out.println("Brief Statemant of Complaint: ");
        Patient p = new Patient(name,surname,sex,dob);
        String briefsoc = sc.nextLine();
        System.out.println("Home Telephone: ");
        String tel1 = sc.nextLine();
        System.out.println("Mobile Telephone: ");
        String tel2 = sc.nextLine();
        ContactDetail cd2 = new ContactDetail(tel1,tel2);
        System.out.println("History Of Previous Treatment: ");
        String history = sc.nextLine();
        System.out.println("Physician/Hospital Treated: ");
        String poht = sc.nextLine();
        BasicComplaint bc2 = new BasicComplaint(briefsoc,history,poht);
        System.out.println("Name of Next Of Kin: ");
        String nameOfKin = sc.nextLine();
        System.out.println("Middle Name of Next Of Kin: ");
        String middleNameOfKin = sc.nextLine();
        System.out.println("Last Name of Next Of Kin: ");
        String lastNameOfKin = sc.nextLine();
        System.out.println("Relation to patient: ");
        String relation = sc.nextLine();
        NextOfKin nok2 = new NextOfKin(nameOfKin,middleNameOfKin,lastNameOfKin,relation);
        
        Patient p2 = PATIENTS_HANDLER.getPatient(PATIENTS_HANDLER.fastRegistryOfPatient(p,bc2,cd2,nok2));
        System.out.println("Patient inserted: " + p2.toString());
        
    }
        
    

    private static void displayPatients() {
        List<Patient> patientsHandlers = PATIENTS_HANDLER.getPatients();
        System.out.println(patientsHandlers.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
    }

    

    private static void insertGeneralyPhysicist(Scanner sc) {
        //String name, String surname,String title;
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Surname: ");
        String surname = sc.nextLine();
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println(GENERALPHYSICIANS_HANDLER.getGeneralPhysician(GENERALPHYSICIANS_HANDLER.insertGeneralPhysician(new GeneralPhysician(name, surname, title))));
        
    }

    private static void insertSpecialist(Scanner sc) {
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Surname: ");
        String surname = sc.nextLine();
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println(SPECIALIZATIONS_HANDLER.getSpecializations().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
        System.out.println("Unesite broj Specializacije: ");
        int spec = sc.nextInt();
        Specialist sp = SPECIALISTS_HANDLER.getSpecialist(SPECIALISTS_HANDLER.insertSpecialist(new Specialist(name,surname,title,SPECIALIZATIONS_HANDLER.getSpecialization(spec))));
        System.out.println(sp);
        
    }

    
    
}
