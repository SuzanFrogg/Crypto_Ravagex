/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import donnees.cles.Cles;
import donnees.messages.Message;
import java.util.HashMap;

/**
 *
 * @author simonetma
 */
public class Univers {
    private static Univers instance;                             //Instance du singleton
    private HashMap<String,Cles> listeClesPubliques;             //Liste des clés publiques repérées par un identifiant unique
    private HashMap<String,Message> listeMessagesPublics;      //Liste des messages publics reprérés par un identifiant unique
    
    /**
     * Constructeur privé du singleton Univers
     */
    private Univers() {
        this.listeClesPubliques = new HashMap<>();
        this.listeMessagesPublics = new HashMap<>();
    } 
    
    /**
     * Getter du singleton
     * @return l'instance du singleton Univers
     */
    public static Univers get() {
        if(instance == null) {
            instance = new Univers();
        }
        return instance;
    }
    
    /**
     * Ajoute une nouvelle série de clés publiques
     * @param identifiant Identifiant de la série de clés publiques
     * @param cles Série de clés publiques
     */
    public static void addCles(String identifiant, Cles cles) {
        get().listeClesPubliques.put(identifiant, cles);
    }
    
    /**
     * Renvoie une série de clés publiques
     * @param identifiant Identifiant de la série de clés
     * @return La série de clés en question
     */
    public static Cles getCles(String identifiant) {
        return get().listeClesPubliques.get(identifiant);
    }
    
    /**
     * Ajoute un nouveau message public
     * @param identifiant Identifiant du message public
     * @param message Message public
     */
    public static void addMessage(String identifiant, Message message) {
        get().listeMessagesPublics.put(identifiant, message);
    }
    
    /**
     * Renvoie un message publique
     * @param identifiant Identifiant du message publique
     * @return Le message en question
     */
    public static Message getMessage(String identifiant) {
        return get().listeMessagesPublics.get(identifiant);
    }
    
    
}

