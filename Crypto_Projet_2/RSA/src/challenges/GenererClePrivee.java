/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenges;

import algorithmes.chiffrement.generateurdecles.GenerateurDeClesRSA;
import donnees.NombreBinaire;
import donnees.cles.Cle;
import donnees.cles.CleBinaire;
import exceptions.ExceptionConversionImpossible;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant la validation du challenge n°20 : Générer la clé privée
 * @author mathy
 */
public class GenererClePrivee extends Challenge{

    
    /**
     * Pour valider le challenge Générer la clé privée, il faut renvoyer la clé
     * privée calculée à partir des trois NombreBinaire reçus : P Q et e
     * @return la clé privée
     * @throws IOException 
     */
    @Override
    public String communicate() throws IOException {
            //Récupération de P puis Q et e
            NombreBinaire p = new NombreBinaire(getMsgReceive());
            setMsgReceive(getClient().receiveMessage());
            NombreBinaire q = new NombreBinaire(getMsgReceive());
            setMsgReceive(getClient().receiveMessage());
            NombreBinaire e = new NombreBinaire(getMsgReceive());
            //Creation du generateur
            GenerateurDeClesRSA generator = new GenerateurDeClesRSA();
            generator.setP(p);
            generator.setQ(q);
            generator.setE(e);
            
            //Execution et renvoi du Nombre décalé
            Cle d = generator.genererClePrivee().getCle("privee");
            return d. ;


    }
    
}
