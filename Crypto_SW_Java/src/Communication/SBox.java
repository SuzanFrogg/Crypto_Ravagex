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
public class SBox extends Client{
      
    
     /**
     * SBox phase2
     *MDP SBow(5) => 0x411520f7 => 1091903735
     */
    @Override
    protected void phase2()
    {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp
        messageAEnvoyer = "1091903735";
        sendMessage(messageAEnvoyer);

        do 
        {
            
        } while(!messageRecu.equals("END")) ;
    }
    
    /**
     * SBox phase 3 //n'existe pas
     * MDP 
     */
    @Override
    protected void phase3()
    {
        
    }

    
    
}
