/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import algorithmes.chiffrement.AlgorithmeRSA;
import donnees.MotBinaire;
import donnees.NombreBinaire;
import donnees.cles.CleBinaire;
import donnees.cles.Cles;
import exceptions.ExceptionConversionImpossible;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alban
 */
public class ChiffrerUnMorceau extends Challenge{

    
    //ATTENTION NE MARCHE PAS ENCORE
    @Override
    public String communicate() throws IOException {
        String res = "ALED";
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        MotBinaire M = new MotBinaire(mot1.asBitSet(), 126);
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n2 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        
        CleBinaire N = new CleBinaire(new MotBinaire(n1.toString()));
        CleBinaire e = new CleBinaire(new MotBinaire(n2.toString()));
        
        Cles cles = new Cles();
        cles.addCle("cleRSA_N", N);
        cles.addCle("cleRSA_e", e);
        
        AlgorithmeRSA a = new AlgorithmeRSA();
        
        try {
            res = a.chiffrerMorceau(M, cles).toString();
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(ChiffrerUnMorceau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
}
