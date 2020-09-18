package com.karlo.gui;

import com.karlo.bl.MedicalPerscriptionsHandler;
import com.karlo.bl.TreatmentsHandler;
import com.karlo.model.MedicalPerscription;
import com.karlo.model.Treatment;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class MedicalPerscriptionAdd {
private static final MedicalPerscriptionsHandler MEDICALPERSCIRPTIONS_HANDLER = new MedicalPerscriptionsHandler();
private static final TreatmentsHandler TREATMENT_HANDLER = new TreatmentsHandler();
    public MedicalPerscriptionAdd(Treatment treatment) {
       
           
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
            createGui(treatment);
        });
    }

    private void createGui(Treatment treatment) {
        JFrame frame = new JFrame("Perscribe Medications");
                //frame.setBackground(java.awt.Color.GRAY);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame,treatment);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
    }

    private void addComponents(JFrame frame, Treatment treatment) {
        JPanel container = new JPanel(new GridBagLayout());
        JLabel lblMed = new JLabel("Perscribe Medications:");
        JTextArea txtMed = new JTextArea("");
                try {
        txtMed.setText(treatment.getMp().getName());
        } catch (Exception e) {
        
        }
         
                
        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        btnSave.setPreferredSize(new Dimension(80,30));
        btnCancel.setPreferredSize(new Dimension(80,30));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(3, 3, 3, 3);
        container.add(lblMed,c);
        c.gridy = 1;
        c.gridwidth = 3;
        Font font = new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT, 11);
        txtMed.setFont(font);
        txtMed.setPreferredSize(new Dimension(500,150));
        container.add(txtMed,c);
        c.gridy = 2;
        c.gridwidth = 1;
        container.add(btnSave,c);
        c.insets = new Insets(3, 86, 3, 3);
        container.add(btnCancel,c);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMed.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out TextArea");
                }
                else{
                    MedicalPerscription med = new MedicalPerscription(txtMed.getText());
                    med.setId(MEDICALPERSCIRPTIONS_HANDLER.insertMedicalPersciption(med)); 
                    treatment.setMp(med);
                    TREATMENT_HANDLER.updateTreatment(treatment, treatment.getId());
                    frame.dispose();
                }
            }
        });
                
        frame.add(container);
        
                
        
    }
    
}
