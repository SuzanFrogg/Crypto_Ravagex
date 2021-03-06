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
 * @author mathy
 */
public class Permutation extends Client{

    @Override
    protected void phase2() {
      String send = "", receive = "";
        //Le MDP
        send = "10010011001110";
        do 
        {
            try {
                //On envoie le message
                this.sendMessage(send);
                //On récupère le message 
                receive = getMessage();
                MotBinaire mot = new MotBinaire(receive);
                MotBinaire image = new MotBinaire(mot.getBitSet(),mot.getTaille());
                for(int emp = 0; emp <8;emp++)
                {
                    switch(emp) {
                        case 0: 
                            image.getBitSet().set(0,mot.getBitSet().get(2));
                            break;
                        case 1: 
                            image.getBitSet().set(1,mot.getBitSet().get(4));
                            break;
                        case 2: 
                            image.getBitSet().set(2,mot.getBitSet().get(6));
                            break;
                        case 3: 
                            image.getBitSet().set(3,mot.getBitSet().get(0));
                            break;
                        case 4: 
                            image.getBitSet().set(4,mot.getBitSet().get(5));
                            break;
                        case 5: 
                            image.getBitSet().set(5,mot.getBitSet().get(1));
                            break;
                        case 6: 
                            image.getBitSet().set(6,mot.getBitSet().get(7));
                            break;
                        case 7: 
                            image.getBitSet().set(7,mot.getBitSet().get(3));
                            break;
                    }
                    
                    send = image.toString();
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!receive.equals("END"));
    }

    @Override
    protected void phase3() {/*Cette partie n'a pas de phase n°3*/}
    
}
