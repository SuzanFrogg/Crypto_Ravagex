/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°4 : Soustraction
 * @author Manon
 * @author Mathys
 */
public class Soustraction extends Challenge {
    
    /**
     * Pour valider le challenge Soustraction, il faut renvoyer la soustraction
     * du nombre binaire 1 et le nombre binaire 2
     * @return la différence entre les deux nombres binaires
     * @throws IOException 
     */
    @Override  
    public String communicate() throws IOException {
        //Récupération des deux nombres binaires
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire mot2 = new NombreBinaire(getMsgReceive());

        //Effectuer la soustraction
        return mot1.soustraction(mot2).toString();
        
    }
    
}
