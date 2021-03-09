/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmes.generateurdecles;

import donnees.cles.Cles;

/**
 *
 * @author simonetma
 */
public interface GenerateurDeCles {
    
    /**
     * Génère une série de clés publique
     * @return une série de clés publique
     */
    public Cles genererClePublique();
    
    /**
     * Génère une série de clés privée
     * @return une série de clés privée
     */
    public Cles genererClePrivee();
    
}
