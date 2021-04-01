/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import donnees.NombreBinaire;
import java.io.IOException;

/**
 * Classe permettant la validation du challenge n°8 : Multiplication
 * @author Albane
 */
public class Multiplication extends Challenge{

    /**
     * Pour valider le challenge Multiplication, il faut valider la multiplication de 
     * deux nombres binaires
     * @return
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        //Récupération des deux nombres binaires
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire mot2 = new NombreBinaire(getMsgReceive());

        //Effectuer la multiplication
        return mot1.multiplication(mot2).toString();
    }
    
}
