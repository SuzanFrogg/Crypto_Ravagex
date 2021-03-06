/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Moteur.binaire.MotBinaire;
import Moteur.binaire.boxes.SBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manon
 */
public class SBoxCom extends Client{
      
    
     /**
     * SBoxCom phase2
MDP SBow(5) => 0x7602d4f7 => 1979897079
     */
    @Override
    protected void phase2()
    {
        String messageRecu = "" ;
        String messageAEnvoyer = "" ;

        //envoie mdp
        messageAEnvoyer = "1979897079";
        sendMessage(messageAEnvoyer);

        do 
        {
            try {

                messageRecu = getMessage();
                MotBinaire mot1 = new MotBinaire(messageRecu);
                
                //récuper le fichier de Sbox
                String currentFolder = System.getProperty("user.dir");
                SBox sbox = new SBox(currentFolder + "/src/Data/sbox.txt");
            
                MotBinaire mot2 = sbox.appliquer(mot1);
                messageAEnvoyer = mot2.toString();
                
                sendMessage(messageAEnvoyer);
                
            } catch (IOException ex) {
                Logger.getLogger(SBoxCom.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!messageRecu.equals("END")) ;
    }
    
    /**
     * SBoxCom phase 3 //n'existe pas
 MDP 
     */
    @Override
    protected void phase3()
    {
        
    }

    
    
}
