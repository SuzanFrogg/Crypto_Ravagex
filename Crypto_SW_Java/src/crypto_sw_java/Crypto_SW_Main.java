package crypto_sw_java;

import Communication.Client;
import Communication.Communication;
import Communication.Conversion;
import Communication.Decryptage;
import Communication.Operation;
import Communication.Permutation;
import Communication.SBoxCom;
import Moteur.binaire.MotBinaire;
import Moteur.binaire.boxes.SBox;
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

        Client client = new Decryptage();
        //this.connexion();
        //this.creationFlux();
        client.boucleDeDiscussion(3);

        } catch (IOException ex) {
        Logger.getLogger(Crypto_SW_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
        
        
        
    }    
      
}
