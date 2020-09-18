package com.karlo.gui;

import com.karlo.bl.AppointmentsHandler;
import com.karlo.bl.GeneralPhysiciansHandler;
import com.karlo.bl.MedicalPerscriptionsHandler;
import com.karlo.bl.PatientsHandler;
import com.karlo.bl.SpecialistsHandler;
import com.karlo.bl.TestsHandler;
import com.karlo.model.Appointment;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.MedicalPerscription;
import com.karlo.model.Patient;
import com.karlo.model.Specialist;
import com.karlo.model.Test;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.UnsupportedLookAndFeelException;



public class MainMenu extends JFrame{
    private static final GeneralPhysiciansHandler GENERALPHYSICIAN_HANDLER = new GeneralPhysiciansHandler();
    private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
    private static final SpecialistsHandler SPECIALISTS_HANDLER = new SpecialistsHandler();
    private static final AppointmentsHandler APPOINTMENTS_HANDLER = new AppointmentsHandler();
    private static final MedicalPerscriptionsHandler MEDICAL_P_HANDLER = new MedicalPerscriptionsHandler();
    private static final TestsHandler TESTS_HANDLER =  new TestsHandler();
    final static String GENPANEL = "General Physicians";
    final static String SPECPANEL = "Specialists";
    
    public MainMenu() throws HeadlessException {
       
           
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
           
        javax.swing.SwingUtilities.invokeLater(() -> {
            createMainMenu();
        });
        
    }

    public void createMainMenu()  {
        JFrame frame = new JFrame("Virgo Hospital");
                
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setResizable(false);
                
                loadMainMenu(frame);
                
                frame.pack();
                frame.setVisible(true);
    }

    public void loadMainMenu(JFrame frame) {
        
        
        JTabbedPane tpMainGui = new JTabbedPane();
        // <editor-fold defaultstate="collapsed" desc="Patient Registration">
        JPanel pnlRegistration = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton btnMiniRegistration = new JButton("Mini Registration Form");
        btnMiniRegistration.setPreferredSize(new Dimension(300,70));
        c.insets = new Insets(3, 3, 3, 3);
        JButton btnComprehensiveRegistration = new JButton("Comprehensive Registration Form");
        
        btnComprehensiveRegistration.setPreferredSize(new Dimension(300,70));
        pnlRegistration.add(btnMiniRegistration,c);
        
        
        btnMiniRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MiniRegistration();
            }
        });
        
        btnComprehensiveRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new ComprehensiveRegistration();
            }
        });
        
        
 
        pnlRegistration.add(btnComprehensiveRegistration,c);
        tpMainGui.add("Patient Registration",pnlRegistration);

        frame.add(tpMainGui);
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="View Patients">
        
        JPanel pnlViewPatients = new JPanel(new GridBagLayout());
        
        JComboBox<GeneralPhysician> cbGeneralPhysician = new JComboBox();
        DefaultComboBoxModel<GeneralPhysician> doctorsModel;
        doctorsModel = new DefaultComboBoxModel<>();
        
        GENERALPHYSICIAN_HANDLER.getGeneralPhysicians().forEach(e -> doctorsModel.addElement(e));
                cbGeneralPhysician.setModel(doctorsModel);
        
        cbGeneralPhysician.setPreferredSize(new Dimension(100,35));
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        pnlViewPatients.add(cbGeneralPhysician,c);
        c.insets = new Insets(3, 220, 3, 3);
      
        JCheckBox cbAllPatients = new JCheckBox("View All Patients");
        pnlViewPatients.add(cbAllPatients,c);
        c.insets = new Insets(3, 3, 3, 3);
        JList<Patient> lPatients = new JList(PATIENTS_HANDLER.getPatients().toArray());
        DefaultListModel<Patient> patientsModel;
        patientsModel = new DefaultListModel<>();
         
        JScrollPane scrollPane1 = new JScrollPane();
         scrollPane1.setViewportView(lPatients);
         lPatients.setLayoutOrientation(JList.VERTICAL);
         
         
         cbAllPatients.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                patientsModel.clear();
                    PATIENTS_HANDLER.getPatients().forEach(ef -> patientsModel.addElement(ef));
                    lPatients.setModel(patientsModel);
                }
                else
                    refreshPatientsList(cbGeneralPhysician, patientsModel, lPatients, cbAllPatients);
                
            }
        });
         
         
        refreshPatientsList(cbGeneralPhysician, patientsModel, lPatients,cbAllPatients);
        cbGeneralPhysician.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbAllPatients.setSelected(false);
                refreshPatientsList(cbGeneralPhysician, patientsModel, lPatients,cbAllPatients);
            }
        });
        
        c.gridx = 0;
        c.gridy = 1;
        scrollPane1.setPreferredSize(new Dimension(500,200));
        pnlViewPatients.add(scrollPane1,c);
        
        
        c.gridy = 2;
        JButton btnEdit = new JButton("Edit Patient");
        c.anchor = GridBagConstraints.WEST;
        btnEdit.setPreferredSize(new Dimension(90,30));
        pnlViewPatients.add(btnEdit,c);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                try {
                    new EditPatient(lPatients.getSelectedValue().getId());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Please select a patient!!");
                }
                
                
            }
        });
        
   
        
        JButton btnApp =  new JButton("Make new appointment");
        
        btnApp.setPreferredSize(new Dimension(160,30));
        c.insets = new Insets(3, 120, 3, 3);
        pnlViewPatients.add(btnApp,c);
        
        btnApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MakeAppointmentForPatient(lPatients.getSelectedValue().getId(),((GeneralPhysician)doctorsModel.getSelectedItem()).getId());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(frame, "Please select a patient!!");
                }
            }
        });
        
        JButton btnMed =  new JButton("Delete Patient");
        btnMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lPatients.getSelectedValue() != null) {
                    if (JOptionPane.showConfirmDialog(frame, "Are you sure?") == JOptionPane.YES_OPTION) {
                        PATIENTS_HANDLER.deletePatient(lPatients.getSelectedValue().getId());
                    }
                    
                    
                    
                }
                else
                    JOptionPane.showMessageDialog(frame, "Select Patient");
            }
        });
        btnMed.setPreferredSize(new Dimension(130,30));
        c.insets = new Insets(3, 312, 3, 3);
        pnlViewPatients.add(btnMed,c);
        
        tpMainGui.add("Patients",pnlViewPatients);
        
        
        JTabbedPane tpPersonel = new JTabbedPane();
        
           //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="View General Physicians">
        
        JPanel pnlGeneralPhysician = new JPanel(new GridBagLayout());
        
        JList<GeneralPhysician> listGP = new JList();
        DefaultListModel<GeneralPhysician> listdoctorsModel;
        listdoctorsModel = new DefaultListModel<>();
        
        refreshGenPhyList(listGP, listdoctorsModel);
        
        JScrollPane scrollPane = new JScrollPane();
         scrollPane.setViewportView(listGP);
         listGP.setLayoutOrientation(JList.VERTICAL);
        
        scrollPane.setPreferredSize(new Dimension(500,200));
        
        c.insets = new Insets(3,3,3,3);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        pnlGeneralPhysician.add(scrollPane,c);
        c.gridy = 1;
       
        JButton btnCreateGP = new JButton("Create");
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 3, 3, 3);
        btnCreateGP.setPreferredSize(new Dimension(80,30));
        pnlGeneralPhysician.add(btnCreateGP,c);
        btnCreateGP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateGeneralPhysician();
            }
        });
        
        JButton btnEditGP =  new JButton("Edit");
        btnEditGP.setPreferredSize(new Dimension(80,30));
        c.insets = new Insets(3, 86, 3, 3);
        pnlGeneralPhysician.add(btnEditGP,c);
        btnEditGP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listGP.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(frame, "Please select Physician");
                }
                else
                new EditGeneralPhysician(listGP.getSelectedValue().getId());
            }
        });
        
        JButton btnDeleteGP =  new JButton("Delete");
        btnDeleteGP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the General Physician?") == JOptionPane.YES_OPTION) {
                    try {
                        GENERALPHYSICIAN_HANDLER.deleteGeneralPhysician(listGP.getSelectedValue().getId());                        
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(frame, "Please select General Physician!");
                    }
                }
                
                   
                
            }
        });
        btnDeleteGP.setPreferredSize(new Dimension(80,30));
        c.insets = new Insets(3, 169, 3, 3);
        pnlGeneralPhysician.add(btnDeleteGP,c);
        tpPersonel.add("General Physicians",pnlGeneralPhysician);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="View Spec">
        c.insets = new Insets(3, 3, 3, 3);
        c.gridy = 0;
        JPanel viewSpec = new JPanel(new GridBagLayout());
        JList<Specialist> listSpec = new JList();
        DefaultListModel<Specialist> specModel;
        specModel = new DefaultListModel<>();
        
        SPECIALISTS_HANDLER.getSpecialists().forEach(ef -> specModel.addElement(ef));
        listSpec.setModel(specModel);
        
        
        JScrollPane scrollPane2 = new JScrollPane();
         scrollPane2.setViewportView(listSpec);
         listSpec.setLayoutOrientation(JList.VERTICAL);
        scrollPane2.setPreferredSize(new Dimension(500,200));
        viewSpec.add(scrollPane2,c);
        c.gridy = 1;
        JButton btnCreateS = new JButton("Create");
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 3, 3, 3);
        btnCreateS.setPreferredSize(new Dimension(80,30));
        viewSpec.add(btnCreateS,c);
        
        JButton btnEditS =  new JButton("Edit");
        btnEditS.setPreferredSize(new Dimension(80,30));
        btnEditS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listSpec.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(frame, "Please choose Specialist");
                }
                else
                new EditSpecialist(listSpec.getSelectedValue().getId());
            }
        });
        c.insets = new Insets(3, 86, 3, 3);
        viewSpec.add(btnEditS,c);
        btnCreateS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new CreateSpecialist();
            }
        });
        JButton btnDeleteS =  new JButton("Delete");
        btnDeleteS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the Specialist?") == JOptionPane.YES_OPTION) {
                    try {
                        SPECIALISTS_HANDLER.deleteSpecialist(listSpec.getSelectedValue().getId());
                        refreshSpecialist(specModel, listSpec);
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(frame, "Please select Specialist!");
                    }
                }
            }
        });
        btnDeleteS.setPreferredSize(new Dimension(80,30));
        c.insets = new Insets(3, 169, 3, 3);
        viewSpec.add(btnDeleteS,c);
        tpPersonel.add("Specialists",viewSpec);
        tpMainGui.add("Personel",tpPersonel);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="View Appointments">
        
        JPanel viewAppointments = new JPanel(new GridBagLayout());
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        String[] choose = { "Upcoming", "All" };
        JComboBox cbApp = new JComboBox(choose);
        c.anchor = GridBagConstraints.WEST;
        viewAppointments.add(cbApp);
        c.gridy = 1;
        
        cbGeneralPhysician.setPreferredSize(new Dimension(200,20));
        JList<Appointment> listApp = new JList();
        JScrollPane spApp = new JScrollPane();
        spApp.setViewportView(listApp);
        DefaultListModel<Appointment> appModel = new DefaultListModel<>();
        listApp.setLayoutOrientation(JList.VERTICAL);
        spApp.setPreferredSize(new Dimension(500,200));
        viewAppointments.add(spApp,c);
        
        setUpcoming(appModel, listApp);
                
        cbApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbApp.getSelectedItem().toString() == "All") {
                    appModel.clear();
        
        APPOINTMENTS_HANDLER.getAppointments().forEach((Appointment ef) -> {
            if ("ERROR".equals(ef.toString())) {
                
            }
            else
                appModel.addElement(ef);});
        
        listApp.setModel(appModel);
                }
                else{
                    appModel.clear();
        
            APPOINTMENTS_HANDLER.getUpcomingAppointments().forEach(ef -> {
            if (ef.toString() == "ERROR") {
                
            }
            else
                appModel.addElement(ef);});
        
        listApp.setModel(appModel);
                    
            }}

            
        });
        c.gridy = 2;
        
        c.insets = new Insets(3, 3, 3, 3);
        JButton btnCreateApp = new JButton("Create Appontment");
        btnCreateApp.setPreferredSize(new Dimension(150,30));
        viewAppointments.add(btnCreateApp,c);
        btnCreateApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new CreateNewAppointment();
            }
        });
        
        c.insets = new Insets(3, 160, 3, 3);
        JButton btnDelApp = new JButton("Delete Appontment");
        btnDelApp.setPreferredSize(new Dimension(150,30));
        viewAppointments.add(btnDelApp,c);
        btnDelApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listApp.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(frame, "Please choose Appointment");
                }
                else{
                    if (JOptionPane.showConfirmDialog(frame, "Are you sure?") == JOptionPane.YES_OPTION) {
                        APPOINTMENTS_HANDLER.deleteAppointment(listApp.getSelectedValue().getId());
                    }
                }
                
            }
        });
        c.insets = new Insets(3, 320, 3, 3);
        JButton btnOpenApp = new JButton("Manage Appointment");
        btnOpenApp.setPreferredSize(new Dimension(150,30));
        viewAppointments.add(btnOpenApp,c);
        btnOpenApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listApp.getSelectedValue() != null) {
                    new ViewTreatment(listApp.getSelectedValue());
                }
                else
                    JOptionPane.showMessageDialog(frame, "Please choose Appointment");
            }
        });
        
        tpMainGui.add("Appointments",viewAppointments);
        // </editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="History">
        
        JTabbedPane tpHistory = new JTabbedPane();
        JPanel historyApp = new JPanel(new GridBagLayout());
        JPanel historyTest = new JPanel(new GridBagLayout());
        JPanel historyMedP = new JPanel(new GridBagLayout());
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(3, 3, 3, 3);
        
        JComboBox<Patient> cbPat = new JComboBox();
        DefaultComboBoxModel<Patient> patModel;
        patModel = new DefaultComboBoxModel<>();
        
        PATIENTS_HANDLER.getPatients().forEach(e -> patModel.addElement(e));
                cbPat.setModel(patModel);
        historyApp.add(cbPat,c);
        
        JList<Appointment> listAppH = new JList();
        JScrollPane spAppH = new JScrollPane();
        spAppH.setViewportView(listAppH);
        DefaultListModel<Appointment> appModel2 = new DefaultListModel<>();
        listAppH.setLayoutOrientation(JList.VERTICAL);
        spAppH.setPreferredSize(new Dimension(500,200));
        listAppH.setPreferredSize(new Dimension(500,200));
        c.gridy = 1;
        APPOINTMENTS_HANDLER.getAppForPatient(((Patient)cbPat.getSelectedItem())).forEach(ef -> appModel2.addElement(ef));
        listAppH.setModel(appModel2);
        historyApp.add(spAppH,c);
        
        cbPat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appModel2.clear();
                APPOINTMENTS_HANDLER.getAppForPatient(((Patient)cbPat.getSelectedItem())).forEach(ef -> appModel2.addElement(ef));
        listAppH.setModel(appModel2);
            }
        });
        tpHistory.add(historyApp,"Appointments");
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(3, 3, 3, 3);
        
        JComboBox<Patient> cbPat2 = new JComboBox();
        DefaultComboBoxModel<Patient> patModel2;
        patModel2 = new DefaultComboBoxModel<>();
        
        PATIENTS_HANDLER.getPatients().forEach(e -> patModel2.addElement(e));
                cbPat2.setModel(patModel2);
                
        historyMedP.add(cbPat2,c);
         
       JList<MedicalPerscription> listMedH = new JList();
        JScrollPane spMedH = new JScrollPane();
        spMedH.setViewportView(listMedH);
        DefaultListModel<MedicalPerscription> appModel3 = new DefaultListModel<>();
        listMedH.setLayoutOrientation(JList.VERTICAL);
        spMedH.setPreferredSize(new Dimension(500,200));
        listMedH.setPreferredSize(new Dimension(500,200));
        c.gridy = 1;
        MEDICAL_P_HANDLER.getMedicalPerscriptionsForPatient(((Patient)cbPat.getSelectedItem())).forEach(ef -> appModel3.addElement(ef));
        listMedH.setModel(appModel3);
        historyMedP.add(spMedH,c);
        tpHistory.add(historyMedP,"Medicines Perscribed");
        cbPat2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appModel3.clear();
                MEDICAL_P_HANDLER.getMedicalPerscriptionsForPatient(((Patient)cbPat2.getSelectedItem())).forEach(ef -> appModel3.addElement(ef));
                listMedH.setModel(appModel3);
            }
        });
        
        c.gridx = 0;
        c.gridy = 0;
        JComboBox<Patient> cbPat3 = new JComboBox();
        DefaultComboBoxModel<Patient> patModel3;
        patModel3 = new DefaultComboBoxModel<>();
        
        PATIENTS_HANDLER.getPatients().forEach(e -> patModel3.addElement(e));
                cbPat3.setModel(patModel3);
                
        historyTest.add(cbPat3,c);
        
        JList<Test> listTestH = new JList();
        JScrollPane spTestH = new JScrollPane();
        spTestH.setViewportView(listTestH);
        DefaultListModel<Test> appModel4 = new DefaultListModel<>();
        listTestH.setLayoutOrientation(JList.VERTICAL);
        spTestH.setPreferredSize(new Dimension(500,200));
        listTestH.setPreferredSize(new Dimension(500,200));
        c.gridy = 1;
        Patient p = PATIENTS_HANDLER.getPatient(cbPat3.getSelectedIndex()+1);
        TESTS_HANDLER.getTestsForPatient(p).forEach(ef1 -> appModel4.addElement(ef1));
        listTestH.setModel(appModel4);
        historyTest.add(spTestH,c);
        tpHistory.add(historyTest,"Tests");
        cbPat3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p2 = PATIENTS_HANDLER.getPatient(cbPat3.getSelectedIndex()+1);
                appModel4.clear();
                TESTS_HANDLER.getTestsForPatient(p2).forEach(ef1 -> appModel4.addElement(ef1));
                listTestH.setModel(appModel4);
            }
        });
        
        listAppH.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                    new ViewTreatment(listAppH.getSelectedValue());
                }
                }
            });
        
        
        tpMainGui.add(tpHistory,"History");
        //</editor-fold>
      
        listApp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listGP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSpec.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listAppH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTestH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMedH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        spAppH.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        spTestH.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        spMedH.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        spAppH.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        spTestH.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        spMedH.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
       
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e){
                
                    refreshCBGenPhy(cbGeneralPhysician, doctorsModel);
                    refreshPatientsList(cbGeneralPhysician, patientsModel, lPatients,cbAllPatients);
                    specModel.clear();
                    SPECIALISTS_HANDLER.getSpecialists().forEach(ef -> specModel.addElement(ef));
                    listSpec.setModel(specModel);
                    refreshGenPhyList(listGP,listdoctorsModel);
                    Patient p2 = PATIENTS_HANDLER.getPatient(cbPat3.getSelectedIndex()+1);
                appModel4.clear();
                TESTS_HANDLER.getTestsForPatient(p2).forEach(ef1 -> appModel4.addElement(ef1));
                listTestH.setModel(appModel4);
                appModel3.clear();
                MEDICAL_P_HANDLER.getMedicalPerscriptionsForPatient(((Patient)cbPat2.getSelectedItem())).forEach(ef -> appModel3.addElement(ef));
                appModel2.clear();
                APPOINTMENTS_HANDLER.getAppForPatient(((Patient)cbPat.getSelectedItem())).forEach(ef -> appModel2.addElement(ef));
        listAppH.setModel(appModel2);
                listMedH.setModel(appModel3);
                    if (cbApp.getSelectedItem().toString() == "Upcoming") {
                        setUpcoming(appModel, listApp);
                        }
                    else 
                    {
                    appModel.clear();
        
        APPOINTMENTS_HANDLER.getAppointments().forEach((Appointment ef) -> {
            if ("ERROR".equals(ef.toString())) {
                
            }
            else
                appModel.addElement(ef);});
        
        listApp.setModel(appModel);
                    }
            

            }

            
        });
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                frame.setPreferredSize(new Dimension(930,590));
    }

    public void setUpcoming(DefaultListModel<Appointment> appModel, JList<Appointment> listApp) {
        appModel.clear();
        
        APPOINTMENTS_HANDLER.getUpcomingAppointments().forEach(ef -> {
            if (ef.toString() == "ERROR") {
                
            }
            else
                appModel.addElement(ef);});
        
        listApp.setModel(appModel);
    }
    

    private void refreshSpecialist(DefaultListModel<Specialist> specModel, JList<Specialist> listSpec) {
        specModel.clear();
        SPECIALISTS_HANDLER.getSpecialists().forEach(ef -> specModel.addElement(ef));
        listSpec.setModel(specModel);
    }
    private void refreshPatientsList(JComboBox<GeneralPhysician> cbGP, DefaultListModel<Patient> patientsModel,JList<Patient> lPatients,JCheckBox all){
    
    
        if (all.isSelected()) {
            
            patientsModel.clear();
                    PATIENTS_HANDLER.getPatients().forEach(ef -> patientsModel.addElement(ef));
                    lPatients.setModel(patientsModel);
            }
            else{
            try {

                
                patientsModel.clear();
                PATIENTS_HANDLER.getPatientsForGeneralPhysician(((GeneralPhysician) cbGP.getSelectedItem()).getId()).forEach(ef -> patientsModel.addElement(ef));
                lPatients.setModel(patientsModel);
                
            } catch (NullPointerException e) {
                
            }}
        }
        
    
        private void refreshCBGenPhy(JComboBox<GeneralPhysician> cbGP, DefaultComboBoxModel<GeneralPhysician> doctorsModel) {
                try {
                    doctorsModel.removeAllElements();
                GENERALPHYSICIAN_HANDLER.getGeneralPhysicians().forEach(e -> doctorsModel.addElement(e));
                cbGP.setModel(doctorsModel);
                
            } catch (Exception e) {
            }
            }
    private void refreshGenPhyList(JList<GeneralPhysician> listGP, DefaultListModel<GeneralPhysician> listdoctorsModel) {
        try {
            listdoctorsModel.clear();
            GENERALPHYSICIAN_HANDLER.getGeneralPhysicians().forEach(e -> listdoctorsModel.addElement(e));
            
            listGP.setModel(listdoctorsModel);
        } catch (Exception e) {
        }
    }

   

}
