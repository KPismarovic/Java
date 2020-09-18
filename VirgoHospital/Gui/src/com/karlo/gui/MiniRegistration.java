package com.karlo.gui;

import com.karlo.bl.PatientsHandler;
import com.karlo.model.BasicComplaint;
import com.karlo.model.ContactDetail;
import com.karlo.model.NextOfKin;
import com.karlo.model.Patient;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;


public class MiniRegistration extends JFrame  {
private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
    public MiniRegistration() throws HeadlessException {
       
           
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
        
        JFrame frame = new JFrame("Fast Registry Of Patient");
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
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets= new Insets(3,3,3,3);
        
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 0;
        container.add(lblName,c);
        c.gridx = 1;
        container.add(txtName,c);
        
        JLabel lblSurname = new JLabel("Surname:");
        JTextField txtSurname = new JTextField();
        txtSurname.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 1;
        container.add(lblSurname,c);
        c.gridx = 1;
        container.add(txtSurname,c);
        
        JLabel lblSex = new JLabel("Sex:");
        String[] sex = {"Male", "Female"};
        JComboBox cbSex = new JComboBox(sex);
        c.gridx = 0;
        c.gridy = 2;
        container.add(lblSex,c);
        c.gridx = 1;
        cbSex.setPreferredSize(new Dimension(137,27));
        container.add(cbSex,c);
        
        JLabel lblDob = new JLabel("Date Of Birth:");
        Vector<Integer> days = new Vector<>();
        for (int i = 1; i < 32; i++) {
            days.add(i);
        }
        Vector<Integer> months = new Vector<>();
        for (int i = 1; i < 13; i++) {
            months.add(i);
        }
        Vector<Integer> years = new Vector<>();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > Calendar.getInstance().get(Calendar.YEAR)-100; i--) {
            years.add(i);
        }
        JComboBox cbDay = new JComboBox(days);
        JComboBox cbMonths = new JComboBox(months);
        JComboBox cbYears = new JComboBox(years);
        c.gridx = 0;
        c.gridy = 3;
        container.add(lblDob,c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        cbDay.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
        container.add(cbDay,c);
        c.insets= new Insets(3,47,3,3);
        cbMonths.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        container.add(cbMonths,c);
        c.insets= new Insets(3,90,3,3);
        container.add(cbYears,c);
        
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(3, 3, 3, 3);
        
        JLabel lblBSOC = new JLabel("Brief Statement of Complaint");
        JTextArea txtBSOC = new JTextArea();
        Font font = new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT, 11);
        txtBSOC.setFont(font);
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.NORTHEAST;
        container.add(lblBSOC,c);
        c.gridx = 1;
        txtBSOC.setPreferredSize(new Dimension(137,80));
        txtBSOC.setLineWrap(true);
        txtBSOC.setWrapStyleWord(true);
        container.add(txtBSOC,c);
        
        c.anchor = GridBagConstraints.NORTHEAST;
        
        JLabel lblTel1 = new JLabel("Home Telephone:");
        JTextField txtTel1 = new JTextField();
        c.gridx = 0;
        c.gridy = 5;
        txtTel1.setPreferredSize(new Dimension(137,27));
        container.add(lblTel1,c);
        c.gridx = 1;
        container.add(txtTel1,c);
        
        JLabel lblTel2 = new JLabel("Mobile Telephone:");
        JTextField txtTel2 = new JTextField();
        txtTel2.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 6;
        container.add(lblTel2,c);
        c.gridx = 1;
        container.add(txtTel2,c);
        
        JLabel lblHistory = new JLabel("History:");
        JTextArea txtHistory = new JTextArea();
        c.gridheight = 1;
        txtHistory.setFont(font);
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.NORTHEAST;
        container.add(lblHistory,c);
        c.gridx = 1;
        txtHistory.setPreferredSize(new Dimension(137,80));
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);
        container.add(txtHistory,c);
        
        c.gridheight = 1;
        
        JLabel lblPhysHos = new JLabel("Physician/Hospital Treated:");
        JTextField txtPhysHos = new JTextField();
        txtPhysHos.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 8;
        container.add(lblPhysHos,c);
        c.gridx = 1;
        container.add(txtPhysHos,c);
        
        
        
        
        JLabel lblNameNOK = new JLabel("Next of Kin Name:");
        JTextField txtNameNOK = new JTextField();
        txtNameNOK.setPreferredSize(new Dimension(137,27));
        c.gridx = 2;
        c.gridy = 0;
        container.add(lblNameNOK,c);
        c.gridx = 3;
        container.add(txtNameNOK,c);
        
        JLabel lblMiddleameNOK = new JLabel("Next of Kin Middlename:");
        JTextField txtMiddleNOK = new JTextField();
        txtMiddleNOK.setPreferredSize(new Dimension(137,27));
        c.gridx = 2;
        c.gridy = 1;
        container.add(lblMiddleameNOK,c);
        c.gridx = 3;
        container.add(txtMiddleNOK,c);
        
        JLabel lblSurnameNOK = new JLabel("Next of Kin Surname:");
        JTextField txtSurnameNOK = new JTextField();
        txtSurnameNOK.setPreferredSize(new Dimension(137,27));
        c.gridx = 2;
        c.gridy = 2;
        container.add(lblSurnameNOK,c);
        c.gridx = 3;
        container.add(txtSurnameNOK,c);
        JLabel lblRel = new JLabel("Relation to Patient:");
        JTextField txtRel = new JTextField();
        txtRel.setPreferredSize(new Dimension(137,27));
        c.gridx = 2;
        c.gridy = 3;
        container.add(lblRel,c);
        c.gridx = 3;
        container.add(txtRel,c);
        
        JButton btnReg = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btnReg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtBSOC.getText().isEmpty() || txtHistory.getText().isEmpty() ||
                        txtPhysHos.getText().isEmpty() || txtTel1.getText().isEmpty() || txtTel2.getText().isEmpty() || txtNameNOK.getText().isEmpty() ||
                        txtSurnameNOK.getText().isEmpty() || txtRel.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out every Text Field :D");
                }
                else{
                Patient p = new Patient(txtName.getText(),txtSurname.getText(),cbSex.getSelectedItem().toString(),
                        cbDay.getSelectedItem().toString()+'.'+cbMonths.getSelectedItem().toString()+ '.' +cbYears.getSelectedItem().toString());
                BasicComplaint bc2 = new BasicComplaint(txtBSOC.getText(),txtHistory.getText(),txtPhysHos.getText());
                ContactDetail cd2 = new ContactDetail(txtTel1.getText(), txtTel2.getText());
                NextOfKin nok2 = new NextOfKin(txtNameNOK.getText(), txtSurname.getText(),txtMiddleNOK.getText(),txtRel.getText());
                Patient p2 = PATIENTS_HANDLER.getPatient(PATIENTS_HANDLER.fastRegistryOfPatient(p,bc2,cd2,nok2));
                JOptionPane.showMessageDialog(frame, PATIENTS_HANDLER.getPatient(p2.getId()).toString());
                frame.dispose();}
            }
        });
        c.gridx=2;
        c.gridy=4;
        btnReg.setPreferredSize(new Dimension(80,30));
        btnCancel.setPreferredSize(new Dimension(80,30));
        container.add(btnReg,c);
        c.gridx=3;
        c.anchor = GridBagConstraints.NORTHWEST;
        container.add(btnCancel,c);
        
        
        
        
        
        txtName.requestFocus();
        frame.add(container);
    }

   
    
}
