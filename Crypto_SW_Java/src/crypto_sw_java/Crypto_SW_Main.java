package crypto_sw_java;

import Communication.Client;
import Communication.Communication;
import Communication.Conversion;
import Communication.Operation;
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
            
        Client client = new Operation();
        client.connexion();
        client.creationFlux();
        
        client.boucleDeDiscussion(3);
        
        } catch (IOException ex) {
        Logger.getLogger(Crypto_SW_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
      
}
