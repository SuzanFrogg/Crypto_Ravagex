package crypto_sw_java;

import Communication.Client;
import Communication.Communication;
import Communication.Conversion;
import Moteur.binaire.MotBinaire;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Thibault
 * @author Manon
 * @author mathy
 */
public class Crypto_SW_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        try {
            
        Client client = new Communication();
        client.connexion();
        client.creationFlux();
        
        client.boucleDeDiscussion(2);
        
        } catch (IOException ex) {
        Logger.getLogger(Crypto_SW_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*MotBinaire test1 = new MotBinaire(5);
        MotBinaire test2 = new MotBinaire(52);
        System.out.println("5 :" + test1.toString());
        System.out.println("52 :" + test2.toString());
        
        MotBinaire test3 = test1.additionMod2p32(test2);
        System.out.println("57 :" + test3.toString());*/
        
    }    
      
}
