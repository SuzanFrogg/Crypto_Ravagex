/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°12 : Puissance Modulaire
 * @author Mathys
 */
public class PuissanceModulaire extends Challenge {

    /**
     * Pour valider le challenge Puissance Modulaire
     * @return un nombre binaire aléatoire entre les deux reçus
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les trois binaires
        NombreBinaire mot = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire exposant = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire m = new NombreBinaire(getMsgReceive());       
        
        //Calcul de la puissance modulaire du Nombre Binaire
        return mot.puissanceModulo(exposant, m).toString();
        }
    
}
