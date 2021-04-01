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
public class DechiffrerUnMorceau extends Challenge{

    @Override
    public String communicate() throws IOException {
        String res = "ALED";
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        MotBinaire M = new MotBinaire(mot1.asBitSet(), 128);
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n2 = new NombreBinaire(getMsgReceive());
        
        CleBinaire N = new CleBinaire(new MotBinaire(n1.toString()));
        CleBinaire d = new CleBinaire(new MotBinaire(n2.toString()));
        
        Cles clesPub = new Cles();
        clesPub.addCle("cleRSA_N", N);
        
        Cles clesPv = new Cles();
        clesPv.addCle("cleRSA_d", d);
        
        AlgorithmeRSA a = new AlgorithmeRSA();
        
        try {
            res = a.dechiffrerMorceau(M, clesPub, clesPv).toString();
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(ChiffrerUnMorceau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
}
