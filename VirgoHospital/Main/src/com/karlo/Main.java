package com.karlo;

import com.karlo.gui.MainMenu;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            
            int switcher = readFromStream(new FileInputStream("init.txt"));
            
            switch (switcher) {
                case 1:
                    com.karlo.console.Main.start();
                    break;
                case 2:
                      new MainMenu();
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int readFromStream(InputStream fileInputStream) {
        try (Scanner sc = new Scanner(fileInputStream)){
            return sc.nextInt();
        }
    }
    
}
