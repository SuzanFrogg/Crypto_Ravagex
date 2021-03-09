/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donnees.cles;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;

/**
 *
 * @author simonetma
 */
public interface Cle {
    
    /**
     * Renvoie la clé sous forme d'une chaine de caractères
     * @return la clé sous forme d'une chaine de caractères
     * @throws ExceptionConversionImpossible si la conversion est impossible
     */
    public String asString() throws ExceptionConversionImpossible;  
    
    /**
     * Renvoie la clé sous forme d'un nombre
     * @return la clé sous forme d'un nombre
     * @throws ExceptionConversionImpossible si la conversion est impossible
     */
    public int asInteger() throws ExceptionConversionImpossible;                    

    /**
     * Renvoie la clé sous forme d'un mot binaire
     * @return
     * @throws ExceptionConversionImpossible
     */
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible;
}

