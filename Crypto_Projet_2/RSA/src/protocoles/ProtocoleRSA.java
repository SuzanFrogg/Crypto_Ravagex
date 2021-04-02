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
        
        Alice.setClesPrivees(clesPrivee);
        Bob.setClesPrivees(clesPrivee);
        
        
        //On se fixe un message clair (non chiffré)
        MotBinaire M = new MotBinaire("01010010010000010101011001000001010001110100010101011000001000000101000001000001010100100101010001011001");
        Message message = new MessageBinaire(M);
        
        //On le fait chiffrer par Alice et on le donne à l’Univers.
        Message messagecode =  Alice.chiffrer(message,clesPublique);
        Univers.addMessage("RSA", messagecode);
            
        //Bob récupère le message depuis l’Univers et le déchiffre.
        Message messagedecode = Bob.dechiffrer(Univers.getMessage("RSA"), clesPublique);

        System.out.println("Message de base : " + message.asString() );
        System.out.println("Message codé : " + messagecode.asString());
        System.out.println("Message décodé : " + messagedecode.asString());
    }
    
}
