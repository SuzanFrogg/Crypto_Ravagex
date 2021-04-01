/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import static algorithmes.chiffrement.RSA.RabinMiller.nombrePremier;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°23 : Nombre Premier
 * @author mathy
 */
public class NombrePremier extends Challenge {

    /**
     * Pour valider le challenge NombrePremier, il faut renvoyer
     * le nombre binaire premier suivant le nombre binaire min
     * @return true si a est un témoin de Miller false sinon
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupération de min
        NombreBinaire min = new NombreBinaire(getMsgReceive());
        
        return nombrePremier(min).toString();
    }
    
}
