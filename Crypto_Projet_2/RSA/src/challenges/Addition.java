/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°2 : Addition
 * @author Manon
 */
public class Addition extends Challenge {

    /**
     * Pour valider le challenge Addition, il faut valider l'addition de 
     * deux nombres binaires
     * @return le résultat de l'addition 
     * @throws IOException 
     */
    @Override        
    public String communicate() throws IOException {

        //Récupération des deux nombres
        NombreBinaire nb1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire nb2 = new NombreBinaire(getMsgReceive());
        //Addition
        return nb1.addition(nb2).toString();
                  
    }
    
}
