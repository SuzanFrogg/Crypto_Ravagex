/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import algorithmes.chiffrement.Algorithme;
import donnees.cles.Cles;
import donnees.messages.Message;
import exceptions.ExceptionAlgorithmeNonDefini;
import exceptions.ExceptionCryptographie;

/**
 *
 * @author simonetma
 */
public class Personne {
    
    private String nom;                 //Nom de la personne
    private Algorithme algorithme;      //Algorithme utilisé 
    private Cles clesPrivees;           //Clés privées de cette personne
    
    /**
     * Constructeur d'une personne
     * @param nom Le nom de la personne
     */
    public Personne(String nom) {
        this.nom = nom;
    }
    
    /**
     * Renvoie le nom de la personne
     * @return le nom
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Fixe l'algorithme utilisé par la personne
     * @param algorithme algorithme utilisé
     */
    public void setAlgorithme(Algorithme algorithme) {
        this.algorithme = algorithme;
    }
    
    /**
     * Renvoie L'algorithme utilisé par la personne
     * @return L'algorithme utilisé
     */
    public Algorithme getAlgorithme() {
        return this.algorithme;
    }
    
    /**
     * Fixe la série de clés privées utilisées par la personne
     * @param cles Série de clés privée's utilisées par la personne
     */
    public void setClesPrivees(Cles cles) {
        this.clesPrivees = cles;
    }
    
    /**
     * Demande à l'algorithme de chiffrer le message
     * @param message Message à chiffrer
     * @param clesPubliques Série de clés publiques utilisée pour le chiffrement
     * @return Message chiffré
     * @throws ExceptionAlgorithmeNonDefini si l'algorithme n'est pas défini
     * @throws ExceptionCryptographie si l'algorithme renvoie une erreur
     */
    public Message chiffrer(Message message,Cles clesPubliques) throws ExceptionAlgorithmeNonDefini, ExceptionCryptographie {
        if(this.algorithme == null) {
            throw new ExceptionAlgorithmeNonDefini(this);
        }
        return this.algorithme.chiffrer(message,clesPubliques,this.clesPrivees);
    }
    
    /**
     * Demande à l'algorithme de déchiffrer le message
     * @param message Message à déchiffrer
     * @param clesPubliques Série de clés publiques utilisée pour le déchiffrement
     * @return Message déchiffré
     * @throws ExceptionAlgorithmeNonDefini si l'algorithme n'est pas défini 
     * @throws ExceptionCryptographie si l'algorithme renvoie une erreur
     */
    public Message dechiffrer(Message message,Cles clesPubliques) throws ExceptionAlgorithmeNonDefini, ExceptionCryptographie {
        if(this.algorithme == null) {
            throw new ExceptionAlgorithmeNonDefini(this);
        }
        return this.algorithme.dechiffrer(message, clesPubliques, clesPrivees);
    }
    
}

