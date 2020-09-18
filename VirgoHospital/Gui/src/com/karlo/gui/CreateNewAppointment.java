package com.karlo.gui;

import com.karlo.bl.AppointmentsHandler;
import com.karlo.bl.GeneralPhysiciansHandler;
import com.karlo.bl.PatientsHandler;
import com.karlo.model.Appointment;
import com.karlo.model.GeneralPhysician;
import com.karlo.model.Patient;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CreateNewAppointment {
    private static final GeneralPhysiciansHandler GENERALPHYSICIAN_HANDLER = new GeneralPhysiciansHandler();
    private static final AppointmentsHandler APPOINTMENTS_HANDLER = new AppointmentsHandler();
    private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
    public CreateNewAppointment() {
       
           
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
            createGui();
        });
    }

    private void createGui() {
        JFrame frame = new JFrame("Create Appointment");
                //frame.setBackground(java.awt.Color.GRAY);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
        
        
    }

    private void addComponents(JFrame frame) {
        JPanel pnlApp = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        
        JComboBox<GeneralPhysician> cbGP = new JComboBox();
        DefaultComboBoxModel<GeneralPhysician> doctorsModel;
        doctorsModel = new DefaultComboBoxModel<>();
        
        GENERALPHYSICIAN_HANDLER.getGeneralPhysicians().forEach(e -> doctorsModel.addElement(e));
        cbGP.setModel(doctorsModel);
        
        cbGP.setPreferredSize(new Dimension(200,27));
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        pnlApp.add(cbGP,c);
        
        
        Vector<Integer> days = new Vector<>();
        for (int i = 1; i < 32; i++) {
            days.add(i);
        }
        Vector<Integer> months = new Vector<>();
        for (int i = 1; i < 13; i++) {
            months.add(i);
        }
        Vector<Integer> years = new Vector<>();
        
        years.add(Calendar.getInstance().get(Calendar.YEAR));
        years.add(Calendar.getInstance().get(Calendar.YEAR)+1);
        
        JComboBox cbDay = new JComboBox(days);
        JComboBox cbMonths = new JComboBox(months);
        JComboBox cbYears = new JComboBox(years);
        c.gridx = 0;
        c.gridy = 1;
        
        c.anchor = GridBagConstraints.WEST;
        cbDay.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
        pnlApp.add(cbDay,c);
        c.insets= new Insets(3,47,3,3);
        cbMonths.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        pnlApp.add(cbMonths,c);
        c.insets= new Insets(3,90,3,3);
        pnlApp.add(cbYears,c);
        
        JComboBox<Patient> cbP = new JComboBox();
        DefaultComboBoxModel<Patient> pModel;
        pModel = new DefaultComboBoxModel<>();
        
        PATIENTS_HANDLER.getPatients().forEach(e -> pModel.addElement(e));
        cbP.setModel(pModel);
        
        cbP.setPreferredSize(new Dimension(200,27));
        c.insets = new Insets(3, 3, 3, 3);
        c.gridy = 2;
        pnlApp.add(cbP,c);
        JButton btnCreate = new JButton("Create");
        JButton btnCancel = new JButton("Cancel");
        
        c.insets = new Insets(3, 2, 3, 3);
        c.gridy = 3;
        pnlApp.add(btnCreate,c);
        c.insets = new Insets(3, 75, 3, 3);
        pnlApp.add(btnCancel,c);
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = (int)cbYears.getSelectedItem();
                int month = (int)cbMonths.getSelectedItem();
                int day = (int)cbDay.getSelectedItem();
                
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.DAY_OF_MONTH, day);
                java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
                
                try {
                    APPOINTMENTS_HANDLER.createAppointment(new GeneralPhysician(((GeneralPhysician)cbGP.getSelectedItem()).getId()), new Patient(((Patient)cbP.getSelectedItem()).getId()), new Appointment(date));
                    JOptionPane.showMessageDialog(frame, date.toString());
                    frame.dispose();
                } catch (Exception ef) {
                    ef.printStackTrace();
                }
                
            }
        });
        frame.add(pnlApp);
    }
        
    
        
}
