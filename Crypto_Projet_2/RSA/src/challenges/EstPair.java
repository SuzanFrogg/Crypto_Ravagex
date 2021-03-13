/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°7 : Est Pair
 * @author mathy
 */
public class EstPair extends Challenge {  

    /**
     * Pour valider le challenge EstPair, il faut vérifier si le nombre reçu est pair ou non
     * @return "true" si le dernier digit est pair, "false" Sinon
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        return new NombreBinaire(getMsgReceive()).estPair()? "true":"false";
    }
    
}
