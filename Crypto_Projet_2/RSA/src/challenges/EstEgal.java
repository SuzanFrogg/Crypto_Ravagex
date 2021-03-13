/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°6 : Est Egal
 * @author Manon
 */
public class EstEgal extends Challenge {

    /**
     * Pour valider le challenge EstEgal, il faut vérifier l'égalité entre
     * deux nombres binaires donnés
     * @return "true" s'ils sont égaux, "false" sinon
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les deux nombres binaires
        NombreBinaire nb1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire nb2 = new NombreBinaire(getMsgReceive());

        //Retourner "true" si il y a égalité.
        return (nb1.estEgal(nb2))? "true" : "false";
    }
}
