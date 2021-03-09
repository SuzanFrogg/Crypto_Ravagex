/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Communication permettant la communication avec la partie Communication du jar
 * @author Manon
 */
public class Communication extends Client{
      
    
     /**
     * communication phase2
     *MDP Communication Phase 2 : ANEWHOPE
     */
    @Override
    protected void phase2() {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp
        messageAEnvoyer = "ANEWHOPE";
        sendMessage(messageAEnvoyer);

    }
    
    /**
     * Communication phase 3
     * MDP Communication Phase 3 : Mvy aol Ltwlyvy (Algo de Cesar +7) : For the Emperor
     */
    @Override
    protected void phase3() {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoi du MDP
        messageAEnvoyer = "For the Emperor";
        sendMessage(messageAEnvoyer);

        do {

            try {
                //Reception du message du serveur
                messageRecu = getMessage();
                
                //Envoi du message de réponse
                messageAEnvoyer = messageRecu;
                sendMessage(messageAEnvoyer);
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } while(!messageRecu.equals("END")) ;
        
    }
}