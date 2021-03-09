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
 * Classe Permutation permettant la communication avec la partie Permutation du jar
 * @author mathy
 */
public class Permutation extends Client{

    @Override
    protected void phase2() {
      String send = "", receive = "";
        //Envoi du MDP
        send = "10010011001110";
        do {
            try {
                //On envoie le message
                this.sendMessage(send);
                //On récupère le message 
                receive = getMessage();
                MotBinaire mot = new MotBinaire(receive);
                //Execution de la permutation
                MotBinaire image = permuterMot8(mot);
                send = image.toString();
                
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(!receive.equals("END"));
    }
    
    
    @Override
    protected void phase3() {/*Cette partie n'a pas de phase n°3*/}
    
    /**
     * Fonction permettant la permutation d'un mot binaire sur 8 bit sur un 
     * schéma fixe
     * @param initial le mot à permuter
     * @return le mot permuté
     */
    public MotBinaire permuterMot8(MotBinaire initial) {
        MotBinaire image = new MotBinaire(initial.getBitSet(),8);
        image.getBitSet().set(0,initial.getBitSet().get(2));
        image.getBitSet().set(1,initial.getBitSet().get(4));
        image.getBitSet().set(2,initial.getBitSet().get(6));
        image.getBitSet().set(3,initial.getBitSet().get(0));
        image.getBitSet().set(4,initial.getBitSet().get(5));
        image.getBitSet().set(5,initial.getBitSet().get(1));
        image.getBitSet().set(6,initial.getBitSet().get(7));
        image.getBitSet().set(7,initial.getBitSet().get(3));      
        return image;
     }
}
