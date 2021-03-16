/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°10 : Modulo
 * @author mathy
 */
public class Modulo extends Challenge{

    /**
     * Pour valider le challenge Modulo: il faut renvoyer le reste de la division
     * euclidienne des deux Nombres Binaires recus
     * @return le reste sous forme de NombreBinaire
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les deux binaires
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire mot2 = new NombreBinaire(getMsgReceive());
        
        //Renvoi le reste de la division euclidienne
        return mot1.modulo(mot2).toString();
    }
    
}
