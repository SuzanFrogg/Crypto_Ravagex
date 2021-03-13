/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°5 : Est Inférieur
 * @author Manon
 */
public class EstInferieur extends Challenge {

    /**
     * Pour valider le challenge Est Inférieur, il faut vérifier si le premier nombre
     * binaire reçu est inférieur que le second
     * @return "true" si le 1er nombre binaire est inférieur au second, sinon "false"
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les deux binaires
        NombreBinaire nb1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire nb2 = new NombreBinaire(getMsgReceive());

        //Retourner si le nb1 est bien inférieur ou égale à nb2
        return (nb1.estInferieurA(nb2))? "true" : "false";
    }
    
}
