/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.MotBinaire;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°3 : Décalage
 * @author Mathys
 */
public class Decalage extends Challenge {

    /**
     * Pour valider le challenge Décalage, il faut renvoyer le nombre binaire
     * décalé d'un nombre donné de 0
     * @return le nombre binaire décalé
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupération du NombreBinaire et du décalage demandé
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        int decalage = Integer.parseInt(getMsgReceive());
        //Execution et renvoi du Nombre décalé
        return mot1.decalage(decalage).toString();
    }
    
}
