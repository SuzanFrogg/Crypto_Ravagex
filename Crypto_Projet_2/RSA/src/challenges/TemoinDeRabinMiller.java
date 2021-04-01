/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import algorithmes.chiffrement.RSA.RabinMiller;
import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°16 : Temoin De Rabin Miller
 * @author Mathys
 */
public class TemoinDeRabinMiller extends Challenge{

    /**
     * Pour valider le challenge TemoinDeRabinMiller, il faut renvoyer
     * true si a est un témoin de Miller
     * @return true si a est un témoin de Miller false sinon
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupération de n puis a
        NombreBinaire n = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire a = new NombreBinaire(getMsgReceive());
        return RabinMiller.temoin(n,a)? "true":"false";
    }
    
}
