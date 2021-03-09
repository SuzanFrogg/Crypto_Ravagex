/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donnees.cles;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;
import java.util.HashMap;

/**
 *
 * @author simonetma
 */
public class Cles {
    private HashMap<String,Cle> listeCles;                      //Liste des clés identifiées par leur nom
    /**
     * Constructeur (simple initialisation)
     */
    public Cles() {
        this.listeCles = new HashMap<>();
    }
    
    /**
     * Renvoie la clé identifiée par le nom saisi en paramètre
     * @param nom : nom de la clé demandée
     * @return La clé ayant ce nom
     */
    public Cle getCle(String nom) {
        return this.listeCles.get(nom);
    }
    
    /**
     * Ajoute une nouvelle clé identifiée par le nom donné en paramètre
     * @param nom Nom de la clé
     * @param cle la Clé
     */
    public void addCle(String nom, Cle cle) {
        this.listeCles.put(nom, cle);
    }
    
}

