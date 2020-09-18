package com.karlo.gui;

import com.karlo.bl.LifeStyleHandler;
import com.karlo.bl.ProfessionDetailsHandler;
import com.karlo.bl.ContactDetailsHandler;
import com.karlo.bl.PatientsHandler;
import com.karlo.model.BasicComplaint;
import com.karlo.model.ContactDetail;
import com.karlo.model.ImportantMedicalComplaint;
import com.karlo.model.LifeStyle;
import com.karlo.model.NextOfKin;
import com.karlo.model.Patient;
import com.karlo.model.PersonAddress;
import com.karlo.model.PersonalDetails;
import com.karlo.model.ProfessionDetail;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class EditPatient {
    
    private static final PatientsHandler PATIENTS_HANDLER = new PatientsHandler();
    private static final ContactDetailsHandler CONTACTS_DETAILS_HANDLER = new ContactDetailsHandler();
     private static final ProfessionDetailsHandler PROFESSIONAL_DETAILS_HANDLER = new ProfessionDetailsHandler();
      private static final LifeStyleHandler LIFESTYLE_HANDLER = new LifeStyleHandler();
    public EditPatient(int idPatient) {
         
          
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
            createGui(idPatient);
        });
    }

    private void createGui(int idPatient) {
        JFrame frame = new JFrame("Edit Patient");
               
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame,idPatient);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
    }

    private void addComponents(JFrame frame,int idPatient) {
       
        Patient p = PATIENTS_HANDLER.getPatient(idPatient);
        ContactDetail cd = CONTACTS_DETAILS_HANDLER.getContactDetail(idPatient);
        LifeStyle ls = LIFESTYLE_HANDLER.getLifeStyle(idPatient);
        ProfessionDetail prd = PROFESSIONAL_DETAILS_HANDLER.getProfessionDetail(idPatient);
        
           JTabbedPane tpRegistration = new JTabbedPane();
        
        // <editor-fold defaultstate="collapsed" desc="Personal details">
        JPanel pnlBasicDetails = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets= new Insets(3,3,3,3);
        
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField(p.getName());
        txtName.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 0;
        pnlBasicDetails.add(lblName,c);
        c.gridx = 1;
        pnlBasicDetails.add(txtName,c);
        
        JLabel lblSurname = new JLabel("Surname:");
        JTextField txtSurname = new JTextField(p.getSurname());
        txtSurname.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 1;
        pnlBasicDetails.add(lblSurname,c);
        c.gridx = 1;
        pnlBasicDetails.add(txtSurname,c);
        
        JLabel lblSex = new JLabel("Sex:");
        String[] sex = {"Male", "Female"};
        JComboBox cbSex = new JComboBox(sex);
        c.gridx = 0;
        c.gridy = 2;
        pnlBasicDetails.add(lblSex,c);
        c.gridx = 1;
        cbSex.setPreferredSize(new Dimension(137,30));
        pnlBasicDetails.add(cbSex,c);
        
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
        pnlBasicDetails.add(lblDob,c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        cbDay.setSelectedIndex(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
        pnlBasicDetails.add(cbDay,c);
        c.insets= new Insets(3,47,3,3);
        cbMonths.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        pnlBasicDetails.add(cbMonths,c);
        c.insets= new Insets(3,90,3,3);
        pnlBasicDetails.add(cbYears,c);
        
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(3, 3, 3, 3);
        
        tpRegistration.add("Basic details", pnlBasicDetails);
        
        // </editor-fold>
        
        
        // <editor-fold defaultstate="collapsed" desc="Contact details">
        JPanel pnlContactDetails = new JPanel(new GridBagLayout());
        
        JLabel lblPresentAdress = new JLabel("Present Adress");
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        pnlContactDetails.add(lblPresentAdress,c);
        
        JLabel lblDoorNumber = new JLabel("Door Number:");
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactDetails.add(lblDoorNumber,c);
        JTextArea txtDrNo = new JTextArea();
        c.gridx = 1;
        txtDrNo.setPreferredSize(new Dimension(137,27));
        txtDrNo.setLineWrap(true);
        txtDrNo.setWrapStyleWord(true);
        pnlContactDetails.add(txtDrNo,c);
        
        JLabel lblStreet = new JLabel("Street:");
        c.gridx = 0;
        c.gridy = 2;
        pnlContactDetails.add(lblStreet,c);
         JTextArea txtStreet = new JTextArea();
        c.gridx = 1;
        txtStreet.setPreferredSize(new Dimension(137,27));
        txtStreet.setLineWrap(true);
        txtStreet.setWrapStyleWord(true);
        pnlContactDetails.add(txtStreet,c);
        
        JLabel lblArea = new JLabel("Area:");
        c.gridx = 0;
        c.gridy = 3;
        pnlContactDetails.add(lblArea,c);
        JTextArea txtArea = new JTextArea();
        c.gridx = 1;
        txtArea.setPreferredSize(new Dimension(137,27));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        pnlContactDetails.add(txtArea,c);
        
         JLabel lblCity = new JLabel("City:");
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactDetails.add(lblCity,c);
        JTextArea txtCity = new JTextArea();
        c.gridx = 3;
        txtCity.setPreferredSize(new Dimension(137,27));
        txtCity.setLineWrap(true);
        txtCity.setWrapStyleWord(true);
        pnlContactDetails.add(txtCity,c);
        
        JLabel lblState = new JLabel("State:");
        c.gridx = 2;
        c.gridy = 2;
        pnlContactDetails.add(lblState,c);
        JTextArea txtState = new JTextArea();
        c.gridx = 3;
        txtState.setPreferredSize(new Dimension(137,27));
        txtState.setLineWrap(true);
        txtState.setWrapStyleWord(true);
        pnlContactDetails.add(txtState,c);
        
        JLabel lblPincode = new JLabel("Pincode:");
        c.gridx = 2;
        c.gridy = 3;
        pnlContactDetails.add(lblPincode,c);
        JTextArea txtPincode = new JTextArea();
        c.gridx = 3;
        txtPincode.setPreferredSize(new Dimension(137,27));
        txtPincode.setLineWrap(true);
        txtPincode.setWrapStyleWord(true);
        pnlContactDetails.add(txtPincode,c);
        
        
        JLabel lblPermanentAdress = new JLabel("Permanent Adress");
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        pnlContactDetails.add(lblPermanentAdress,c);
        
        JLabel lblDoorNumber2 = new JLabel("Door Number:");
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactDetails.add(lblDoorNumber2,c);
        JTextArea txtDrNo2 = new JTextArea();
        c.gridx = 1;
        txtDrNo2.setPreferredSize(new Dimension(137,27));
        txtDrNo2.setLineWrap(true);
        txtDrNo2.setWrapStyleWord(true);
        pnlContactDetails.add(txtDrNo2,c);
        
        JLabel lblStreet2 = new JLabel("Street:");
        c.gridx = 0;
        c.gridy = 6;
        pnlContactDetails.add(lblStreet2,c);
         JTextArea txtStreet2 = new JTextArea();
        c.gridx = 1;
        txtStreet2.setPreferredSize(new Dimension(137,27));
        txtStreet2.setLineWrap(true);
        txtStreet2.setWrapStyleWord(true);
        pnlContactDetails.add(txtStreet2,c);
        
        JLabel lblArea2 = new JLabel("Area:");
        c.gridx = 0;
        c.gridy = 7;
        pnlContactDetails.add(lblArea2,c);
        JTextArea txtArea2 = new JTextArea();
        c.gridx = 1;
        txtArea2.setPreferredSize(new Dimension(137,27));
        txtArea2.setLineWrap(true);
        txtArea2.setWrapStyleWord(true);
        pnlContactDetails.add(txtArea2,c);
        
         JLabel lblCity2 = new JLabel("City:");
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactDetails.add(lblCity2,c);
        JTextArea txtCity2 = new JTextArea();
        c.gridx = 3;
        txtCity2.setPreferredSize(new Dimension(137,27));
        txtCity2.setLineWrap(true);
        txtCity2.setWrapStyleWord(true);
        pnlContactDetails.add(txtCity2,c);
        
        JLabel lblState2 = new JLabel("State:");
        c.gridx = 2;
        c.gridy = 6;
        pnlContactDetails.add(lblState2,c);
        JTextArea txtState2 = new JTextArea();
        c.gridx = 3;
        txtState2.setPreferredSize(new Dimension(137,27));
        txtState2.setLineWrap(true);
        txtState2.setWrapStyleWord(true);
        pnlContactDetails.add(txtState2,c);
        
        JLabel lblPincode2 = new JLabel("Pincode:");
        c.gridx = 2;
        c.gridy = 7;
        pnlContactDetails.add(lblPincode2,c);
        JTextArea txtPincode2 = new JTextArea();
        c.gridx = 3;
        txtPincode2.setPreferredSize(new Dimension(137,27));
        txtPincode2.setLineWrap(true);
        txtPincode2.setWrapStyleWord(true);
        pnlContactDetails.add(txtPincode2,c);
        

        JLabel lblTel1 = new JLabel("Home Telephone:");
        JTextField txtTel1 = new JTextField(cd.getTelephoneHome());
        c.gridx = 0;
        c.gridy = 8;
        txtTel1.setPreferredSize(new Dimension(137,27));
        pnlContactDetails.add(lblTel1,c);
        c.gridx = 1;
        pnlContactDetails.add(txtTel1,c);
        
        JLabel lblTel2 = new JLabel("Work Telephone:");
        JTextField txtTel2 = new JTextField(cd.getTelephoneWork());
        c.gridx = 0;
        c.gridy = 9;
        txtTel2.setPreferredSize(new Dimension(137,27));
        pnlContactDetails.add(lblTel2,c);
        c.gridx = 1;
        pnlContactDetails.add(txtTel2,c);
        
        JLabel lblMobile = new JLabel("Mobile:");
        JTextField txtMobile = new JTextField(cd.getMobile());
        txtMobile.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 10;
        pnlContactDetails.add(lblMobile,c);
        c.gridx = 1;
        pnlContactDetails.add(txtMobile,c);
        
        JLabel lblPager = new JLabel("Pager:");
        JTextField txtPager = new JTextField(cd.getPager());
        c.gridx = 0;
        c.gridy = 11;
        txtPager.setPreferredSize(new Dimension(137,27));
        pnlContactDetails.add(lblPager,c);
        c.gridx = 1;
        pnlContactDetails.add(txtPager,c);
        
        JLabel lblFax = new JLabel("Fax:");
        JTextField txtFax = new JTextField(cd.getFax());
        c.gridx = 0;
        c.gridy = 12;
        txtFax.setPreferredSize(new Dimension(137,27));
        pnlContactDetails.add(lblFax,c);
        c.gridx = 1;
        pnlContactDetails.add(txtFax,c);
        
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField(cd.getEmail());
        txtEmail.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 13;
        pnlContactDetails.add(lblEmail,c);
        c.gridx = 1;
        pnlContactDetails.add(txtEmail,c);
        
         tpRegistration.add("Contact details", pnlContactDetails);
        
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Contact next-of-kin">
        JPanel pnlContactNextOfKin = new JPanel(new GridBagLayout());
        
        JLabel lblNextOfKinName = new JLabel("First Name:");
        JTextField txtNextOfKinName = new JTextField();
        txtNextOfKinName.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 0;
        pnlContactNextOfKin.add(lblNextOfKinName,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtNextOfKinName,c);
        
         JLabel lblNextOfKinMiddle = new JLabel("Middle Name:");
        JTextField txtNextOfKinNameMiddle = new JTextField();
        txtNextOfKinNameMiddle.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 1;
        pnlContactNextOfKin.add(lblNextOfKinMiddle,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtNextOfKinNameMiddle,c);
        
        JLabel lblNextOfKinNameSurname = new JLabel("Surname:");
        JTextField txtNextOfKinNameSurname = new JTextField();
        txtNextOfKinNameSurname.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 2;
        pnlContactNextOfKin.add(lblNextOfKinNameSurname,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtNextOfKinNameSurname,c);
        
         JLabel lblRelation = new JLabel("Relationship to patient:");
        JTextField txtRelation = new JTextField();
        txtRelation.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 3;
        pnlContactNextOfKin.add(lblRelation,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtRelation,c);
        
        JLabel lblContactAdress = new JLabel("Contact Adress");
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        pnlContactNextOfKin.add(lblContactAdress,c);
        
        JLabel lblDoorNoNOK = new JLabel("Door Number:");
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactNextOfKin.add(lblDoorNoNOK,c);
        JTextArea txtDoorNoNOK = new JTextArea();
        c.gridx = 1;
        txtDoorNoNOK.setPreferredSize(new Dimension(137,27));
        txtDoorNoNOK.setLineWrap(true);
        txtDoorNoNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtDoorNoNOK,c);
        
        JLabel lblStreetNOK = new JLabel("Street:");
        c.gridx = 0;
        c.gridy = 6;
        pnlContactNextOfKin.add(lblStreetNOK,c);
         JTextArea txtStreetNOK = new JTextArea();
        c.gridx = 1;
        txtStreetNOK.setPreferredSize(new Dimension(137,27));
        txtStreetNOK.setLineWrap(true);
        txtStreetNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtStreetNOK,c);
        
        JLabel lblAreaNOK = new JLabel("Area:");
        c.gridx = 0;
        c.gridy = 7;
        pnlContactNextOfKin.add(lblAreaNOK,c);
        JTextArea txtAreaNOK = new JTextArea();
        c.gridx = 1;
        txtAreaNOK.setPreferredSize(new Dimension(137,27));
        txtAreaNOK.setLineWrap(true);
        txtAreaNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtAreaNOK,c);
        
         JLabel lblCityNOK = new JLabel("City:");
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlContactNextOfKin.add(lblCityNOK,c);
        JTextArea txtCityNOK = new JTextArea();
        c.gridx = 3;
        txtCityNOK.setPreferredSize(new Dimension(137,27));
        txtCityNOK.setLineWrap(true);
        txtCityNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtCityNOK,c);
        
        JLabel lblStateNOK = new JLabel("State:");
        c.gridx = 2;
        c.gridy = 6;
        pnlContactNextOfKin.add(lblStateNOK,c);
        JTextArea txtStateNOK = new JTextArea();
        c.gridx = 3;
        txtStateNOK.setPreferredSize(new Dimension(137,27));
        txtStateNOK.setLineWrap(true);
        txtStateNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtStateNOK,c);
        
        JLabel lblPincodeNOK = new JLabel("Pincode:");
        c.gridx = 2;
        c.gridy = 7;
        pnlContactNextOfKin.add(lblPincodeNOK,c);
        JTextArea txtPincodeNOK = new JTextArea();
        c.gridx = 3;
        txtPincodeNOK.setPreferredSize(new Dimension(137,27));
        txtPincodeNOK.setLineWrap(true);
        txtPincodeNOK.setWrapStyleWord(true);
        pnlContactNextOfKin.add(txtPincodeNOK,c);
        

        JLabel lblTel1NOK = new JLabel("Home Telephone:");
        JTextField txtTel1NOK = new JTextField();
        c.gridx = 0;
        c.gridy = 8;
        txtTel1NOK.setPreferredSize(new Dimension(137,27));
        pnlContactNextOfKin.add(lblTel1NOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtTel1NOK,c);
        
        JLabel lblTel2NOK = new JLabel("Work Telephone:");
        JTextField txtTel2NOK = new JTextField();
        c.gridx = 0;
        c.gridy = 9;
        txtTel2NOK.setPreferredSize(new Dimension(137,27));
        pnlContactNextOfKin.add(lblTel2NOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtTel2NOK,c);
        
        JLabel lblMobileNOK = new JLabel("Mobile:");
        JTextField txtMobileNOK = new JTextField();
        txtMobileNOK.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 10;
        pnlContactNextOfKin.add(lblMobileNOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtMobileNOK,c);
        
        JLabel lblPagerNOK = new JLabel("Pager:");
        JTextField txtPagerNOK = new JTextField();
        c.gridx = 0;
        c.gridy = 11;
        txtPagerNOK.setPreferredSize(new Dimension(137,27));
        pnlContactNextOfKin.add(lblPagerNOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtPagerNOK,c);
        
        JLabel lblFaxNOK = new JLabel("Fax:");
        JTextField txtFaxNOK= new JTextField();
        c.gridx = 0;
        c.gridy = 12;
        txtFaxNOK.setPreferredSize(new Dimension(137,27));
        pnlContactNextOfKin.add(lblFaxNOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtFaxNOK,c);
        
        JLabel lblEmailNOK = new JLabel("Email:");
        JTextField txtEmailNOK = new JTextField();
        txtEmailNOK.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 13;
        pnlContactNextOfKin.add(lblEmailNOK,c);
        c.gridx = 1;
        pnlContactNextOfKin.add(txtEmailNOK,c);
        
         tpRegistration.add("Contact Next-of-Kin", pnlContactNextOfKin);
        
        // </editor-fold>
        
        
        // <editor-fold defaultstate="collapsed" desc="Personal and Profesional Details">
        
        JPanel pnlPersonDetails = new JPanel(new GridBagLayout());
        
        JLabel lblPersonalDetails = new JLabel("Personal Details");
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        pnlPersonDetails.add(lblPersonalDetails,c);
        
        JLabel lblMaritalStatus = new JLabel("Marital Status:");
        String[] MaritalStatus = {"Single", "Married"};
        JComboBox cbMaritalStatus = new JComboBox(MaritalStatus);
        c.gridx = 0;
        c.gridy = 1;
        pnlPersonDetails.add(lblMaritalStatus,c);
        c.gridx = 1;
        cbMaritalStatus.setPreferredSize(new Dimension(137,30));
        pnlPersonDetails.add(cbMaritalStatus,c);
        
        JLabel lblDependents = new JLabel("No. of Dependents:");
        JTextField txtDependents = new JTextField();
        txtDependents.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 2;
        pnlPersonDetails.add(lblDependents,c);
        c.gridx = 1;
        pnlPersonDetails.add(txtDependents,c);
        
        JLabel lblHeight = new JLabel("Height:");
        JTextField txtHeight = new JTextField();
        txtHeight.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 3;
        pnlPersonDetails.add(lblHeight,c);
        c.gridx = 1;
        pnlPersonDetails.add(txtHeight,c);
        
        JLabel lblWeight = new JLabel("Weight:");
        JTextField txtWeight = new JTextField();
        txtWeight.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 4;
        pnlPersonDetails.add(lblWeight,c);
        c.gridx = 1;
        pnlPersonDetails.add(txtWeight,c);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        String[] BloodType = {"A", "B", "AB", "0"};
        JComboBox cbBloodType = new JComboBox(BloodType);
        c.gridx = 0;
        c.gridy = 5;
        pnlPersonDetails.add(lblBloodType,c);
        c.gridx = 1;
        cbBloodType.setPreferredSize(new Dimension(137,30));
        pnlPersonDetails.add(cbBloodType,c);
        
        JLabel lblProfessionalDetails = new JLabel("Professional Details");
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.CENTER;
        pnlPersonDetails.add(lblProfessionalDetails,c);
        
        JLabel lblOccupation = new JLabel("Occupation:");
        JTextField txtOccupation = new JTextField();
        txtOccupation.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 7;
        pnlPersonDetails.add(lblOccupation,c);
        c.gridx = 1;
        pnlPersonDetails.add(txtOccupation,c);
        
        JLabel lblAnnualIncome = new JLabel("Gross Annual Income:");
        JTextField txtAnnualIncome = new JTextField();
        txtAnnualIncome.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 8;
        pnlPersonDetails.add(lblAnnualIncome,c);
        c.gridx = 1;
        pnlPersonDetails.add(txtAnnualIncome,c);
        
        tpRegistration.add("Person details", pnlPersonDetails);
        
        // </editor-fold>    
          
        // <editor-fold defaultstate="collapsed" desc="Lifestyle">
        
        JPanel pnlLifestyle = new JPanel(new GridBagLayout());
       
        JLabel lblVegetarian = new JLabel("Vegetarian:");
        String[] Vegetarian = {"Yes", "No"};
        JComboBox cbVegetarian = new JComboBox(Vegetarian);
        c.gridx = 0;
        c.gridy = 0;
        pnlLifestyle.add(lblVegetarian,c);
        c.gridx = 1;
        cbVegetarian.setPreferredSize(new Dimension(137,30));
        pnlLifestyle.add(cbVegetarian,c);
        
        JLabel lblSmoker = new JLabel("Smoker:");
        String[] Smoker = {"Yes", "No"};
        JComboBox cbSmoker = new JComboBox(Smoker);
        c.gridx = 0;
        c.gridy = 1;
        pnlLifestyle.add(lblSmoker,c);
        c.gridx = 1;
        cbSmoker.setPreferredSize(new Dimension(137,30));
        pnlLifestyle.add(cbSmoker,c);
        
        JLabel lblAlcoholic = new JLabel("Alcoholic:");
        String[] Alcoholic = {"Yes", "No"};
        JComboBox cbAlcoholic = new JComboBox(Alcoholic);
        c.gridx = 0;
        c.gridy = 2;
        pnlLifestyle.add(lblAlcoholic,c);
        c.gridx = 1;
        cbAlcoholic.setPreferredSize(new Dimension(137,30));
        pnlLifestyle.add(cbAlcoholic,c);
        
        JLabel lblStimulants = new JLabel("Use of Stimulants:");
        JTextField txtStimulants = new JTextField();
        txtStimulants.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 3;
        pnlLifestyle.add(lblStimulants,c);
        c.gridx = 1;
        pnlLifestyle.add(txtStimulants,c);
        
        JLabel lblConsumptionCoffeTea = new JLabel("Consumption of Coffee-Tea/Day:");
        JTextField txtConsumptionCoffeTea = new JTextField();
        txtConsumptionCoffeTea.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 4;
        pnlLifestyle.add(lblConsumptionCoffeTea,c);
        c.gridx = 1;
        pnlLifestyle.add(txtConsumptionCoffeTea,c);
        
        JLabel lblConsumptionDrinks = new JLabel("Consumption of Soft Drinks/Day:");
        JTextField txtConsumptionDrinks = new JTextField();
        txtConsumptionDrinks.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 5;
        pnlLifestyle.add(lblConsumptionDrinks,c);
        c.gridx = 1;
        pnlLifestyle.add(txtConsumptionDrinks,c);
        
        JLabel lblRegularMeals = new JLabel("Regular Meals (Breakfast/Lunch/Dinner):");
        JTextField txtRegularMeals = new JTextField();
        txtRegularMeals.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 6;
        pnlLifestyle.add(lblRegularMeals,c);
        c.gridx = 1;
        pnlLifestyle.add(txtRegularMeals,c);
        
        JLabel lblFood = new JLabel("Eat Home Food/Outside Predominantly:");
        JTextField txtFood = new JTextField();
        txtFood.setPreferredSize(new Dimension(137,30));
        c.gridx = 0;
        c.gridy = 7;
        pnlLifestyle.add(lblFood,c);
        c.gridx = 1;
        pnlLifestyle.add(txtFood,c);
        
        tpRegistration.add("Lifestyle", pnlLifestyle);
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="Complaints">
        
        JPanel pnlComplaints= new JPanel(new GridBagLayout());
        
        JLabel lblBasicComplaints = new JLabel("Basic Complaints");
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        pnlComplaints.add(lblBasicComplaints,c);
        
        JLabel lblStatementOfComplaint = new JLabel("Statement of Complaint");
        JTextArea txtStatementOfComplaint = new JTextArea();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlComplaints.add(lblStatementOfComplaint,c);
        c.gridx = 1;
        txtStatementOfComplaint.setPreferredSize(new Dimension(137,80));
        txtStatementOfComplaint.setLineWrap(true);
        txtStatementOfComplaint.setWrapStyleWord(true);
        pnlComplaints.add(txtStatementOfComplaint,c);
        
        JLabel lblHistory = new JLabel("History:");
        JTextArea txtHistory = new JTextArea();
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 2;
        pnlComplaints.add(lblHistory,c);
        c.gridx = 1;
        txtHistory.setPreferredSize(new Dimension(137,80));
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);
        pnlComplaints.add(txtHistory,c);
        
        JLabel lblPhysicianTreated = new JLabel("Physician/Hospital Treated:");
        JTextArea txtPhysicianTreated = new JTextArea();
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        pnlComplaints.add(lblPhysicianTreated,c);
        c.gridx = 1;
        txtPhysicianTreated.setPreferredSize(new Dimension(137,80));
        txtPhysicianTreated.setLineWrap(true);
        txtPhysicianTreated.setWrapStyleWord(true);
        pnlComplaints.add(txtPhysicianTreated,c);
        
        
        JLabel lblMedicalComplaints = new JLabel("Important Medical Complaints");
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        pnlComplaints.add(lblMedicalComplaints,c);
        
        JLabel lblDiabetic = new JLabel("Diabetic:");
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.NORTHEAST;
        pnlComplaints.add(lblDiabetic,c);
        JTextArea txtDiabetic = new JTextArea();
        c.gridx = 1;
        txtDiabetic.setPreferredSize(new Dimension(137,27));
        txtDiabetic.setLineWrap(true);
        txtDiabetic.setWrapStyleWord(true);
        pnlComplaints.add(txtDiabetic,c);
        
        JLabel lblHypertensive = new JLabel("Hypertensive:");
        c.gridx = 0;
        c.gridy = 6;
        pnlComplaints.add(lblHypertensive,c);
         JTextArea txtHypertensive = new JTextArea();
        c.gridx = 1;
        txtHypertensive.setPreferredSize(new Dimension(137,27));
        txtHypertensive.setLineWrap(true);
        txtHypertensive.setWrapStyleWord(true);
        pnlComplaints.add(txtHypertensive,c);
        
        JLabel lblCardiacCondition = new JLabel("Cardiac Condition:");
        c.gridx = 0;
        c.gridy = 7;
        pnlComplaints.add(lblCardiacCondition,c);
        JTextArea txtCardiacCondition = new JTextArea();
        c.gridx = 1;
        txtCardiacCondition.setPreferredSize(new Dimension(137,27));
        txtCardiacCondition.setLineWrap(true);
        txtCardiacCondition.setWrapStyleWord(true);
        pnlComplaints.add(txtCardiacCondition,c);
        
        JLabel lblRespiratoryCondition = new JLabel("Respiratory Condition:");
        c.gridx = 2;
        c.gridy = 5;
        pnlComplaints.add(lblRespiratoryCondition,c);
        JTextArea txtRespiratoryCondition = new JTextArea();
        c.gridx = 3;
        txtRespiratoryCondition.setPreferredSize(new Dimension(137,27));
        txtRespiratoryCondition.setLineWrap(true);
        txtRespiratoryCondition.setWrapStyleWord(true);
        pnlComplaints.add(txtRespiratoryCondition,c);
        
        JLabel lblDigestiveCondition = new JLabel("Digestive Condition:");
        c.gridx = 2;
        c.gridy = 6;
        pnlComplaints.add(lblDigestiveCondition,c);
        JTextArea txtDigestiveCondition = new JTextArea();
        c.gridx = 3;
        txtDigestiveCondition.setPreferredSize(new Dimension(137,27));
        txtDigestiveCondition.setLineWrap(true);
        txtDigestiveCondition.setWrapStyleWord(true);
        pnlComplaints.add(txtDigestiveCondition,c);
        
        JLabel lblOrthopedicCondition = new JLabel("Orthopedic Condition:");
        c.gridx = 2;
        c.gridy = 7;
        pnlComplaints.add(lblOrthopedicCondition,c);
        JTextArea txtOrthopedicCondition = new JTextArea();
        c.gridx = 3;
        txtOrthopedicCondition.setPreferredSize(new Dimension(137,27));
        txtOrthopedicCondition.setLineWrap(true);
        txtOrthopedicCondition.setWrapStyleWord(true);
        pnlComplaints.add(txtOrthopedicCondition,c);
        

        JLabel lblMuscularCondition = new JLabel("Muscular Condition:");
        JTextField txtMuscularCondition = new JTextField();
        c.gridx = 0;
        c.gridy = 8;
        txtMuscularCondition.setPreferredSize(new Dimension(137,27));
        pnlComplaints.add(lblMuscularCondition,c);
        c.gridx = 1;
        pnlComplaints.add(txtMuscularCondition,c);
        
        JLabel lblNeurologicalCondition = new JLabel("Neurological Condition:");
        JTextField txtNeurologicalCondition = new JTextField();
        c.gridx = 0;
        c.gridy = 9;
        txtNeurologicalCondition.setPreferredSize(new Dimension(137,27));
        pnlComplaints.add(lblNeurologicalCondition,c);
        c.gridx = 1;
        pnlComplaints.add(txtNeurologicalCondition,c);
        
        JLabel lblKnownAllergies = new JLabel("Known Allergies:");
        JTextField txtKnownAllergies = new JTextField();
        txtKnownAllergies.setPreferredSize(new Dimension(137,27));
        c.gridx = 2;
        c.gridy = 8;
        pnlComplaints.add(lblKnownAllergies,c);
        c.gridx = 3;
        pnlComplaints.add(txtKnownAllergies,c);
        
        JLabel lblReactionToDrugs = new JLabel("Known Adverse Reaction to Specific Drugs:");
        JTextField txtReactionToDrugs = new JTextField();
        c.gridx = 2;
        c.gridy = 9;
        txtReactionToDrugs.setPreferredSize(new Dimension(137,27));
        pnlComplaints.add(lblReactionToDrugs,c);
        c.gridx = 3;
        pnlComplaints.add(txtReactionToDrugs,c);
        
        JLabel lblSurgeriesHistory = new JLabel("Major Surgeries (History):");
        JTextField txtSurgeriesHistory = new JTextField();
        txtSurgeriesHistory.setPreferredSize(new Dimension(137,27));
        c.gridx = 0;
        c.gridy = 10;
        pnlComplaints.add(lblSurgeriesHistory,c);
        c.gridx = 1;
        pnlComplaints.add(txtSurgeriesHistory,c);
        
        tpRegistration.add("Contact details", pnlComplaints);
        
         
        // </editor-fold> 
        
       

        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p = new Patient(txtName.getText(),txtSurname.getText(),cbSex.getSelectedItem().toString(),
                        cbDay.getSelectedItem().toString()+'.'+cbMonths.getSelectedItem().toString()+ '.' +cbYears.getSelectedItem().toString());
                BasicComplaint bc = new BasicComplaint(txtStatementOfComplaint.getText(),txtHistory.getText(),txtPhysicianTreated.getText());
                ContactDetail cd = new ContactDetail(txtTel1.getText(), txtTel2.getText(), txtMobile.getText(),txtPager.getText(),txtEmail.getText(),
                        txtFax.getText());
                NextOfKin nok = new NextOfKin(txtNextOfKinName.getText(), txtNextOfKinNameSurname.getText(),txtNextOfKinNameMiddle.getText(),
                        txtRelation.getText());
                ProfessionDetail prd = new ProfessionDetail(txtOccupation.getText(),txtAnnualIncome.getText());
                LifeStyle ls = new LifeStyle(cbVegetarian.getSelectedItem().toString(),cbSmoker.getSelectedItem().toString(),cbAlcoholic.getSelectedItem().toString(),
                        txtStimulants.getText(),txtConsumptionCoffeTea.getText(),txtConsumptionDrinks.getText(),txtRegularMeals.getText(),txtFood.getText());
                ImportantMedicalComplaint imc = new ImportantMedicalComplaint(txtDiabetic.getText(),txtHypertensive.getText(),txtCardiacCondition.getText(),
                        txtRespiratoryCondition.getText(),txtDigestiveCondition.getText(),txtOrthopedicCondition.getText(),txtMuscularCondition.getText(),
                        txtNeurologicalCondition.getText(),txtKnownAllergies.getText(),txtReactionToDrugs.getText(),txtSurgeriesHistory.getText());
                PersonalDetails ped = new PersonalDetails(cbMaritalStatus.getSelectedItem().toString(),txtDependents.getText(),txtHeight.getText(),txtWeight.getText(),
                        cbBloodType.getSelectedItem().toString());
                ContactDetail cdnok = new ContactDetail(txtTel1NOK.getText(), txtTel2NOK.getText(), txtMobileNOK.getText(),txtPagerNOK.getText(),txtEmailNOK.getText(),
                        txtFaxNOK.getText());
                PersonAddress pa = new PersonAddress(txtDrNo.getText(), txtStreet.getText(), txtArea.getText(), txtCity.getText(), txtState.getText(),
                        txtPincode.getText());
                PersonAddress pa2 = new PersonAddress(txtDrNo2.getText(), txtStreet2.getText(), txtArea2.getText(), txtCity2.getText(), txtState2.getText(),
                        txtPincode2.getText());
                PersonAddress panok = new PersonAddress(txtDoorNoNOK.getText(), txtStreetNOK.getText(), txtAreaNOK.getText(), txtCityNOK.getText(), txtStateNOK.getText(),
                        txtPincodeNOK.getText());
                Patient p2 = PATIENTS_HANDLER.getPatient(PATIENTS_HANDLER.ComprehensiveRegistryOfPatient(p,cd,nok,bc,prd,ls,imc,ped,cdnok,pa,pa2,panok));
                JOptionPane.showMessageDialog(frame, PATIENTS_HANDLER.getPatient(p2.getId()).toString());
                frame.dispose();
                
            }
        });
        c.gridx=2;
        c.gridy=10;
        btnSave.setPreferredSize(new Dimension(90,30));
        btnCancel.setPreferredSize(new Dimension(90,30));
        pnlComplaints.add(btnSave,c);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx=3;
        pnlComplaints.add(btnCancel,c);
        
        frame.add(tpRegistration);
        
    }
    
}
