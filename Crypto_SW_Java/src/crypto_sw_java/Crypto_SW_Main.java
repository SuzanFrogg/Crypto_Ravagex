package crypto_sw_java;

import Communication.Client;
import Moteur.binaire.MotBinaire;
import java.io.IOException;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Thibault
 */
public class Crypto_SW_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
      //System.out.println((char) Integer.parseInt("01000001",2));         
      BitSet Bit = new BitSet();
      int taille = 16;
        System.out.println("Premier oct pour A");
      for(int i = 0; i<8;i++) {
          boolean state = i == 1 || i == 7 ? true : false;
          Bit.set(i,state);
          int oct = state ? 1 : 0;
          System.out.print(oct);
      } 
      System.out.println("\n"+Bit.toString());

        System.out.println("\nDeuxième oct pour E");
      for(int i = 9; i<17;i++) {
          boolean state = i == 9 || i == 11 || i == 15 ? true : false;
          Bit.set(i,state);
          int oct = state ? 1 : 0;
          System.out.print(oct);
      }
      
        System.out.println("\n"+Bit.toString());
       
      String res = "";  
        //Les bits se lisent de droite à gauche donc il faut inverser le tout 
        //Avant de pouvoir lire 
        char[] toReverse = new char[taille/8];
        System.out.println("Taille de toReverse : "+toReverse.length);
        //Pour aller d'Octet en octet
        for(int i = 0;i<taille;i+=8) {
            System.out.println("Itérateur : "+i);
            //On récupère un bit
            //Les bits sont enregistrés dans le bitset avec le LSB à droite
            //Il faut donc inversé le bit avant d'en retirer le caractères
            String oct = "";
            for(int index = 8;index>0;index--)
            {
               //true : 1 || false : 0
               oct += Bit.get(index+i) ? 1 : 0 ;
               int l = index+i;
               System.out.println("index : "+l+ "\n"+oct);
            }
            System.out.println("char "+i/8+" "+(char)Integer.parseInt(oct,2));
            toReverse[i/8]=((char)Integer.parseInt(oct,2));
            for(int h=0;h<toReverse.length;h++)
            {
                System.out.println(h+" "+toReverse[h]);
            }
            
        }
        for(int c = toReverse.length-1; c>=0;c--) {
                res += toReverse[c];
                System.out.println("it : "+c + " "+ toReverse[c]);
            }
        System.out.println("res : "+ res);
    } 
    
    
      
      
}
