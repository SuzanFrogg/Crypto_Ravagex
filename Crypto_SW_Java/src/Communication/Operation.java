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
public class Operation extends Client{
      
    
     /**
     * Operation phase2
     *MDP A,EF-KT => convertis en décimaux => 65446970457584
     */
    @Override
    protected void phase2()
    {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp
        messageAEnvoyer = "65446970457584";

        do 
        {
            try 
            {
                sendMessage(messageAEnvoyer);
                messageRecu = getMessage();
                
                if(!messageRecu.equals("END")) 
                {
                    
                    MotBinaire mot1 = new MotBinaire(messageRecu);
                    
                    messageRecu = getMessage();
                    MotBinaire mot2 = new MotBinaire(messageRecu);
                    
                    MotBinaire mot3 = mot1.xor(mot2);
                    messageAEnvoyer = mot3.toString();                
                }
            } catch (IOException ex) 
            {
                Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!messageRecu.equals("END")) ;
    }
    
    /**
     * Operation phase 3
     * MDP  51 11 33 42 32 22 11 51 => Carré de Polybe en U/V => UAMQLGAV
     */
    @Override
    protected void phase3()
    {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp 
        messageAEnvoyer = "UAMQLGAV";

        do 
        {
            try 
            {
                sendMessage(messageAEnvoyer);
                messageRecu = getMessage();   
                
                if(!messageRecu.equals("END")) 
                {
                    MotBinaire mot1 = new MotBinaire(messageRecu);
                    
                    messageRecu = getMessage();
                    MotBinaire mot2 = new MotBinaire(messageRecu);
                    
                    MotBinaire mot3 = mot1.additionMod2p32(mot2);
                    messageAEnvoyer = mot3.toString();
                }
            } catch (IOException ex) 
            {
                Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!messageRecu.equals("END")) ;
    }

    
    
}
