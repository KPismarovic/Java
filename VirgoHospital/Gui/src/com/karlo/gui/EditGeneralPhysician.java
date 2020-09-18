package com.karlo.gui;

import com.karlo.bl.GeneralPhysiciansHandler;
import com.karlo.model.GeneralPhysician;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class EditGeneralPhysician {
    private static final GeneralPhysiciansHandler GENERALPHYSICIAN_HANDLER = new GeneralPhysiciansHandler();
    public EditGeneralPhysician(int idGP) {
       
           
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
            createGui(idGP);
        });
    }

    private void createGui(int idGP) {
        JFrame frame = new JFrame("Edit Physician");
                //frame.setBackground(java.awt.Color.GRAY);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame,idGP);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
    }

    private void addComponents(JFrame frame,int idGP) {
        JPanel pnlCreateGP = new JPanel(new GridBagLayout());
        GeneralPhysician GP1 = GENERALPHYSICIAN_HANDLER.getGeneralPhysician(idGP);
        JLabel lblName = new JLabel("Name:");
        JLabel lblSurname = new JLabel("Surname:");
        JLabel lblTitle = new JLabel("Title:");
        JTextField txtName = new JTextField(GP1.getName());
        JTextField txtSurname = new JTextField(GP1.getSurname());
        JTextField txtTitle = new JTextField(GP1.getTitle());
        txtName.setPreferredSize(new Dimension(137,27));
        txtSurname.setPreferredSize(new Dimension(137,27));
        txtTitle.setPreferredSize(new Dimension(137,27));       
        JButton btnConfirm = new JButton("Confirm");
        JButton btnCancel = new JButton("Cancel");
        GridBagConstraints c = new GridBagConstraints();
        btnConfirm.setPreferredSize(new Dimension(75,27));
        btnCancel.setPreferredSize(new Dimension(75,27));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new  Insets(3,3,3,3);
        c.anchor = GridBagConstraints.WEST;
        pnlCreateGP.add(lblName,c);
        c.gridx = 1;
        pnlCreateGP.add(txtName,c);
        c.gridx = 0;
        c.gridy = 1;
        pnlCreateGP.add(lblSurname,c);
        c.gridx = 1;
        pnlCreateGP.add(txtSurname,c);
        c.gridx = 0;
        c.gridy = 2;
        pnlCreateGP.add(lblTitle,c);
        c.gridx = 1;
        pnlCreateGP.add(txtTitle,c);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        pnlCreateGP.add(btnConfirm,c);
        c.gridwidth = 2;
        c.insets = new Insets(3, 3, 3, 71);
        pnlCreateGP.add(btnCancel,c);
        frame.add(pnlCreateGP);
               
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtName.getText().isEmpty() && !txtSurname.getText().isEmpty() && !txtTitle.getText().isEmpty()) {
                    GeneralPhysician GP = new GeneralPhysician(txtName.getText(), txtSurname.getText(), txtTitle.getText());
                    GENERALPHYSICIAN_HANDLER.updateGeneralPhysician(GP, GP1.getId());
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frame,"Please fill out every TextField");
            }
        });
    }
    
}
