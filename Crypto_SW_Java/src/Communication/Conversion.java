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
 * @author mathy 
 */
public class Conversion extends Client{
      
    
     /**
     * Conversion phase2
     *MDP YKOTO!U␣PRT␣S␣RTL␣␣AEM␣␣OEOOO␣HS␣RI␣ avec ANAKIN double transposition => YOU SHOOT LIKE A STORMTROOPER ! 
     */
    @Override
    protected void phase2()
    {
        String receive = "", send = "";
        
        //Envoi du MDP
        send = "YOU SHOOT LIKE A STORMTROOPER !";
        do 
        {
            try {
                //On envoie le message
                this.sendMessage(send);
                //On récupère le message qui est soit un long soit un char
                receive = getMessage();
                //On fait la convertion de cette reception
                try {
                    //On tente d'abord la conversion de la reception tel un long
                    /*
                    Long l = Long.parseLong(receive);
                    MotBinaire mot = new MotBinaire(l);
                    send = mot.toString();
                    */
                    
                    send = new MotBinaire(Long.parseLong(receive)).toString();
                }
                //Si cela n'est pas possible, faire la conversion tel un char
                catch(NumberFormatException ex) {
                    /*
                    char c = receive.charAt(0);
                    MotBinaire mot = new MotBinaire(receive.charAt(0));
                    send = mot.toString();
                    */
                    
                    send = new MotBinaire(receive.charAt(0)).toString();
                }
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!receive.equals("END"));
    }
    
    /**
     * Conversion phase 3
     * MDP DARKVADOR
     */
    @Override
    protected void phase3() {
        String receive = "", send = "";
        int nbSeq =0;
        //Le MDP
        send = "DARKVADOR";
        do 
        {
            try {
                //On envoie le message
                this.sendMessage(send);
                //On récupère le message 
                receive = getMessage();
                
                if(nbSeq <5) {
                    nbSeq++;
                    send = String.valueOf(new MotBinaire(receive).asInteger());
                } else {
                    send = new MotBinaire(receive).asString();
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!receive.equals("END"));
    }

    
    
}
