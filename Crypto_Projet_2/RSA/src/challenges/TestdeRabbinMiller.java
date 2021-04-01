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
 * Classe permettant la validation du challenge n°19 : Test De Rabin Miller
 * @author mathy
 */
public class TestdeRabbinMiller extends Challenge{


    /**
     * Pour valider le challenge TestDeRabinMiller, il faut renvoyer
     * true si a reussi le test de Rabin Miller
     * @return true si a reussi le test de Rabin Miller false sinon
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        NombreBinaire n = new NombreBinaire(getMsgReceive());
        return RabinMiller.testRabinMiller(n)? "true":"false";    
    }
}
