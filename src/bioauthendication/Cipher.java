/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioauthendication;

import java.util.ArrayList;

/**
 *
 * @author anees
 */
public class Cipher {
    
    public static String generateCipherText(String mesurements){
        String[] split = mesurements.split("-");
        ArrayList<String> xorbin = xor(split);
        String cipherText="";
        for (String xorbin1 : xorbin) {
//            System.out.println(xorbin1);
            int length = xorbin1.length();
            int decimalValue = Integer.parseInt(xorbin1, 2);
            cipherText += decimalValue+" ";
            cipherText += length+" ";
        }
        return cipherText;
    }
    
    public static String getOriginal(String cipher){
        String[] splitted = cipher.split(" ");
        String mesurements="";
        ArrayList<String> xorbin = notxor(splitted);
        for (String xorbin1 : xorbin) {
            int decimalValue = Integer.parseInt(xorbin1, 2);
            mesurements += decimalValue+" ";
        }
        return mesurements;
    }
    
    private static ArrayList<String> notxor(String[] splitted){
        ArrayList<String> bin= new ArrayList<>();
        for (int i = 1; i < splitted.length/2+1; i++) {
            String binstr ="";
            String temp = Integer.toBinaryString(Integer.parseInt(splitted[2*(i-1)]));
            int length0 = Integer.parseInt(splitted[(i*2)-1])-temp.length();
            for (int j = 0; j < length0; j++) {
                binstr+="0";
            }       
            binstr += temp;
//            System.out.println(splitted[i-1]);
//            System.out.println(temp);
            bin.add(i-1, doxor(binstr));
        }
        return bin;
    }
    
    private static ArrayList<String> xor(String[] splitted){
        
        ArrayList<String> bin= new ArrayList<>();
        for (int i = 0; i < splitted.length; i++) {
           String binstr = Integer.toBinaryString(Integer.parseInt(splitted[i]));
//           System.out.println(binstr);
           bin.add(i, doxor(binstr));
        }
        return bin;
    }
    
    private static String doxor(String binString){
        String xorbin = "";
        for (int i = 0; i < binString.length(); i++) {
            int j = i/2;
            if ((j % 2) == 0){
                if("0".equals(binString.substring(i,i+1))){
                    xorbin+='1';
                }else{
                    xorbin+='0';
                }
            }else{
                if("1".equals(binString.substring(i,i+1))){
                    xorbin+='1';
                }else{
                    xorbin+='0';
                }
            }
        }
        return xorbin;
    }
}
