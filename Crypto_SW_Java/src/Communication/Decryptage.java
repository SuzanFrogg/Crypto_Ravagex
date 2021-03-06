/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Moteur.binaire.MotBinaire;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manon
 */
public class Decryptage extends Client{
      
    
     /**
     * Decryptage phase2
     *MDP
     */
    @Override
    protected void phase2()
    {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp
        messageAEnvoyer = "";
        sendMessage(messageAEnvoyer);

        do 
        {
            
        } while(!messageRecu.equals("END")) ;
    }
    
    /**
     * Decryptage phase 3
     * n'existe pas
     */
    @Override
    protected void phase3()
    {
       
    }

    
    
}
