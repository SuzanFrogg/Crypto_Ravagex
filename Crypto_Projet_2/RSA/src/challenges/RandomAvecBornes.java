/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°14 : Random avec bornes
 * @author mathy
 */
public class RandomAvecBornes extends Challenge {

    /**
     * Pour valider le challenge Random avec bornes
     * @return 
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les deux binaires
        NombreBinaire min = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire max = new NombreBinaire(getMsgReceive());
        
        return NombreBinaire.random(min, max).toString();
        }
    
}