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
 * Classe permettant la validation du challenge n°18 : Déchiffrer un morceau
 * @author Albane
 */
public class DechiffrerUnMorceau extends Challenge{

    /**
     * Pour valider le challenge Déchiffrer un morceau, il faut déchiffrer le morceau reçu
     * à l'aide des clés
     * @return
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
        String res = "ALED";
        
        //On récupère la série de triplets envoyé par le serveur
        NombreBinaire mot1 = new NombreBinaire(getMsgReceive());
        MotBinaire M = new MotBinaire(mot1.asBitSet(), 128);
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n1 = new NombreBinaire(getMsgReceive());
        setMsgReceive(getClient().receiveMessage());
        NombreBinaire n2 = new NombreBinaire(getMsgReceive());
        
        //On initialise les clés N et d
        CleBinaire N = new CleBinaire(new MotBinaire(n1.toString()));
        CleBinaire d = new CleBinaire(new MotBinaire(n2.toString()));
        
        //On ajoute N à une liste de clés (liste de clés publiques)
        Cles clesPub = new Cles();
        clesPub.addCle("cleRSA_N", N);
        
        //On ajoute d à une liste de clés (liste de clés privées)
        Cles clesPv = new Cles();
        clesPv.addCle("cleRSA_d", d);
        
        //On créé un algorithmeRSA
        AlgorithmeRSA a = new AlgorithmeRSA();
        
        //On essaie de déchiffrer
        try {
            res = a.dechiffrerMorceau(M, clesPub, clesPv).toString();
        } catch (ExceptionConversionImpossible ex) {
            Logger.getLogger(ChiffrerUnMorceau.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //On retourne le résultat
        return res;
    }
    
}
