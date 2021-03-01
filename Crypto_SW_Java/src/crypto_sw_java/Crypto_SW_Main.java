package crypto_sw_java;

import Communication.Client;
import Moteur.binaire.MotBinaire;
import java.io.IOException;
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
       
        try {
        Client client = new Client();
        client.connexion();
        client.creationFlux();
        
        client.boucleDeDiscussion("communication",2);
        
        } catch (IOException ex) {
        Logger.getLogger(Crypto_SW_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* MotBinaire bin = new MotBinaire("oui");
        System.out.println(bin);
        System.out.println(bin.asString());*/
        
        
        
    }
    
}
