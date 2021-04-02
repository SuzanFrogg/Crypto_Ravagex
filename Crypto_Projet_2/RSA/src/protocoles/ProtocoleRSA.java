/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocoles;

import algorithmes.chiffrement.Algorithme;
import algorithmes.chiffrement.AlgorithmeRSA;
import algorithmes.chiffrement.generateurdecles.GenerateurDeClesRSA;
import donnees.MotBinaire;
import donnees.cles.Cles;
import donnees.messages.Message;
import donnees.messages.MessageBinaire;
import entites.Personne;
import entites.Univers;
import exceptions.ExceptionCryptographie;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Albane
 */
public class ProtocoleRSA implements Protocole{

    @Override
    public void executer() throws ExceptionCryptographie {
        //On crée Alice et Bob
        Personne Alice = new Personne("Alice");
        Personne Bob = new Personne("Bob");
        //On donne à Alice un algorithme RSA.
        Algorithme a = new AlgorithmeRSA();
        Alice.setAlgorithme(a);
        Bob.setAlgorithme(a);
        
        GenerateurDeClesRSA generateurRSA = new GenerateurDeClesRSA(); 
        Cles clesPublique =  generateurRSA.genererClePublique();
        Cles clesPrivee =  generateurRSA.genererClePrivee();

        System.out.println("cles");
        System.out.println("N : "+clesPublique.getCle("cleRSA_N").asMotBinaire().toString());
        System.out.println("e : "+clesPublique.getCle("cleRSA_e").asMotBinaire().toString());
        System.out.println("d : "+clesPrivee.getCle("cleRSA_d").asMotBinaire().toString());

        System.out.println("set cle");
        Alice.setClesPrivees(clesPrivee);
        Bob.setClesPrivees(clesPrivee);
        
        System.out.println("set Mot");
        //On se fixe un message clair (non chiffré)
        MotBinaire M = new MotBinaire("010000110010000001000101010100110101010001010110010100100100000101001001010011010100010101001110010101000101001101010101010100000100010101010010010000110100010101001100010000010100110101000001010100100100001101001000010001010100001001001001010001010100");
        Message message = new MessageBinaire(M);
        System.out.println("message : "+message.asMotBinaire().asString());
        //On le fait chiffrer par Alice et on le donne à l’Univers.
        Message messagecode =  Alice.chiffrer(message,clesPublique);
        
        Univers.addMessage("RSA", messagecode);
            
        //Bob récupère le message depuis l’Univers et le déchiffre.
        Message messagedecode = Bob.dechiffrer(Univers.getMessage("RSA"), clesPublique);

        System.out.println("Fin");
        System.out.println("Message de base : " + message.asString()+" | "+ message.asString().length());
        System.out.println("Message codé : " + messagecode.asString()+" | "+ messagecode.asString().length());
        System.out.println("Message décodé : " + messagedecode.asString()+" | "+ messagedecode.asString().length());
    }
    
}
