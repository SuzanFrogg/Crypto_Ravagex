/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°15 : Inverse Modulaire
 * @author Mathys
 */
public class InverseModulaire extends Challenge{

    /**
     * Pour valider le challenge InverseModulaire: il faut renvoyer le resultat 
     * du calcul de l'inverse modulaire des deux Nombres Binaires recus
     * @return l'inverse modulaire des deux Nombres Binaires
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupérer les deux binaires
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire mot2 = new NombreBinaire(getMsgReceive());
        
        //Renvoi le résultat du calcul du PGCD
        return mot1.inverseModulaire(mot2).toString();    
    }
    
}