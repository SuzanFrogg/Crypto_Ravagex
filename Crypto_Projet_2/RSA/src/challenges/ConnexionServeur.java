/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import java.io.IOException;

/**
 * Communication de la connexion, challenge n°1
 * @author Manon
 * @author Thibault
 * @author Mathys
 */
public class ConnexionServeur extends Challenge{
    /**
     * Pour valider la connexion, il faut renvoyer le nombre reçu plus 1
     * @return Integer reçu + 1
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        return String.valueOf(Integer.parseInt(getMsgReceive())+1);
    }
}
