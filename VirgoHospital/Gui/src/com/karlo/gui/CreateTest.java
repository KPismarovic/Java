package com.karlo.gui;

import com.karlo.bl.TestsHandler;
import com.karlo.model.Patient;
import com.karlo.model.Test;
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
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class CreateTest {
private static final TestsHandler TESTS_HANDLER = new TestsHandler();
    public CreateTest(Patient p) {
        
           
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
            createGui(p);
        });
    }

    private void createGui(Patient p) {
        JFrame frame = new JFrame("Create Test");
                //frame.setBackground(java.awt.Color.GRAY);
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                
                frame.setResizable(false);
                addComponents(frame,p);
                
                frame.setPreferredSize(new Dimension(930,590));
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 4);
                frame.setLocation(x, y);
                
                frame.pack();
                frame.setVisible(true);
    }

    private void addComponents(JFrame frame,Patient p) {
        JPanel container = new JPanel(new GridBagLayout());
        JLabel lblName = new JLabel("Test:");
        JTextField txtname = new JTextField();
        JLabel lblResult = new JLabel("Result:");
        JTextArea txtResult = new JTextArea();
        txtResult.setPreferredSize(new Dimension(300,100));
        txtname.setPreferredSize(new Dimension(150,27));
        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel:");
        btnSave.setPreferredSize(new Dimension(80,30));
        btnCancel.setPreferredSize(new Dimension(80,30));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        container.add(lblName,c);
        c.gridx = 1;
        container.add(txtname,c);
        Font font = new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT, 11);
        txtResult.setFont(font);
        c.gridx = 0;
        c.gridy = 1;
        container.add(lblResult,c);
        c.gridy = 2;
        c.gridwidth = 4;
        container.add(txtResult,c);
        c.gridx  = 0;
        c.gridy = 3;
        c.gridwidth = 2;
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
                if (txtname.getText().isEmpty() || txtResult.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill out every Testfield");
                }
                else{
                TESTS_HANDLER.insertTest(new Test(txtname.getText(),txtResult.getText(),p));
                frame.dispose();
                }
            }
        });
        
        
        
        frame.add(container);
               
        
    }
    
}
