/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioauthendication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author anees
 */
public class BioAuthendication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
    
    public static void saveUser(String name,String mesurements){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("user", true));
            String cipherText = Cipher.generateCipherText(mesurements);
            bw.write(name + "=>" + cipherText);
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException ex) {
        }
    }
    
    public static String auth(String mesurements) {
        try {
            Scanner s = new Scanner(new File("user"));
            String user = "empty";
            while (s.hasNextLine()) {
                String[] split = s.nextLine().split("=>");
                String name = split[0];
                String chipherText = split[1];
                if (!chipherText.equals("")) {
                    boolean compairMesures = compairMesures(mesurements,Cipher.getOriginal(chipherText));
                    if (compairMesures){
                        user = name;
                    }
                }
            }
            return user;
            
        } catch (FileNotFoundException ex) {

            return "empty";
        }
    }
    private static boolean compairMesures(String mesure, String fromCipher) {
        String[] mesurements = mesure.split("-");
        String[] original = fromCipher.split(" ");
        
        int absDiff = 0;
        for (int i = 0; i < mesurements.length; i++) {
//            System.out.println(mesurements[i]);
//            System.out.println(original);
            int Diff = Math.abs(Integer.parseInt(mesurements[i]) - Integer.parseInt(original[i]));
            if (Diff>=3){
                absDiff+=1;
            }
        }
        return absDiff < 3;
    }
    
}
