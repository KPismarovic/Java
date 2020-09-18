package com.karlo.gui;

import com.karlo.bl.AppointmentsHandler;
import com.karlo.bl.GeneralPhysiciansHandler;
import com.karlo.bl.GeneralTreatmentsHandler;
import com.karlo.bl.PatientsHandler;
import com.karlo.bl.TestsHandler;
import com.karlo.bl.TreatmentsHandler;
import com.karlo.model.Appointment;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.GeneralTreatment;
import com.karlo.model.Treatment;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class ViewTreatment {
private static final AppointmentsHandler APPOINTMENTS_HANDLER = new AppointmentsHandler();
private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
private static final GeneralTreatmentsHandler GENERAL_TREATMENT_HANDLER = new GeneralTreatmentsHandler();
private static final GeneralPhysiciansHandler GP_HANDLER = new GeneralPhysiciansHandler();
private static final TreatmentsHandler TREATMENT_HANDLER = new TreatmentsHandler();
private static final TestsHandler TESTS_HANDLER = new TestsHandler();

    public ViewTreatment(Appointment app) {
       
           
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
            createGui(app);
        });
    }

    private void createGui(Appointment app) {
        JFrame frame = new JFrame("Treatment");
                //frame.setBackground(java.awt.Color.GRAY);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame,app);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
    }

    private void addComponents(JFrame frame, Appointment app) {
        Treatment treatment = APPOINTMENTS_HANDLER.getTreatmentForApp(app.getId());
        GeneralTreatment gTreatment = treatment.getGt();
        GeneralPhysician gp = GP_HANDLER.getGeneralPhysician(gTreatment.getGp().getId());
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel lblDate = new JLabel("Date:");
        
        JLabel lblPatient = new JLabel("Patient:");
        
        JLabel lblGP = new JLabel("General Physician:");
        
        JLabel txtPatient = new JLabel(PATIENTS_HANDLER.getDetailedPatient(treatment.getPatient().getId()).toString());
        
        JLabel txtDate = new JLabel(app.getDate().toLocaleString().substring(0, 8));
        
        JLabel txtGP = new JLabel(gp.toString());
        
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        container.add(lblDate,c);
        c.gridx = 1;
        container.add(txtDate,c);
        
        c.gridx = 0;
        c.gridy = 1;
        container.add(lblPatient,c);
        c.gridx = 1;
        container.add(txtPatient,c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(lblGP,c);
        c.gridx = 1;
        container.add(txtGP,c);
        
        c.gridx = 0;
        c.gridy = 3;
        JLabel lbltTot = new JLabel("Type Of Treatment:");
        JTextField txtTot = new JTextField(gTreatment.getTot());
        txtTot.setPreferredSize(new Dimension(150,27));
        container.add(lbltTot,c);
        c.gridx = 1;
        container.add(txtTot,c);
        
        c.gridx = 0;
        c.gridy = 4;
        JLabel lblD = new JLabel("Diagnosis:");
        JTextArea txtD = new JTextArea(treatment.getDiagnosis());
        Font font = new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT, 11);
        txtD.setFont(font);
        container.add(lblD,c);
        c.gridy = 5;
        c.gridwidth = 3;
        txtD.setPreferredSize(new Dimension(500,150));
        container.add(txtD,c);
        
        c.gridy = 6;
        JLabel lblFee = new JLabel("Fee:");
        JTextField txtFee;
        try {
            txtFee = new JTextField(treatment.getAppointment().getPayment().getCost().setScale(2,BigDecimal.ROUND_UP).toString());
        } catch (Exception e) {
            txtFee = new JTextField();
        }
        txtFee.setPreferredSize(new Dimension(80,27));
        c.gridwidth=1;
        container.add(lblFee,c);
        c.gridx = 1;
        container.add(txtFee,c);
        
        c.gridwidth = 3;
                
        c.gridx = 0;
        c.gridy = 7;
        
        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(80,30));
        container.add(btnSave,c);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTot.getText().isEmpty() || txtD.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Fill out every Textfield");
                }
                else{
                    treatment.setDiagnosis(txtD.getText());
                    gTreatment.setTot(txtTot.getText());
                    treatment.setGt(gTreatment);
                    TREATMENT_HANDLER.updateTreatment(treatment, treatment.getId());
                    
                    frame.dispose();
                
                }
            }
        });
        
        c.insets = new Insets(3, 86, 3, 3);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(80,30));
        container.add(btnCancel,c);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        c.insets = new Insets(3, 169, 3, 3);
        JButton btnMakeTest = new JButton("Make Test");
        btnMakeTest.setPreferredSize(new Dimension(100,30));
        container.add(btnMakeTest,c);
        
         
        c.gridy = 8;
        c.insets = new Insets(3, 3, 3, 3);
        JButton btnMedP = new JButton("Perscribe Medication");
        btnMedP.setPreferredSize(new Dimension(162,30));
        container.add(btnMedP,c);
                
        btnMedP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicalPerscriptionAdd(treatment);
            }
        });
        btnMakeTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateTest(treatment.getPatient());
            }
        });
        
        frame.add(container);
        
    }
    
}
