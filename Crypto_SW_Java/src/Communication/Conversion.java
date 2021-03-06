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
        this.sendMessage(send);
        do 
        {
            try {
                //On récupère le message qui est soit un long soit un char
                receive = getMessage();
                //On fait la convertion de cette reception
                try {
                    Long l = Long.parseLong(receive);
                    send = Long.toBinaryString(l);
                }
                catch(NumberFormatException ex) {
                    //Si ce n'est pas un long, alors faire la conversion d'un char
                    //Stackoverflow est notre ami
                    byte[] array = receive.getBytes();
                    send = Integer.toBinaryString(0x100 + array[0]).substring(1);
                }
                //Envoi du message de réponse
               
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.sendMessage(send);
        } while(!receive.equals("END"));
    }
    
    /**
     * Conversion phase 3
     * MDP DARKVADOR
     */
    @Override
    protected void phase3()
    {
        
    }

    
    
}
