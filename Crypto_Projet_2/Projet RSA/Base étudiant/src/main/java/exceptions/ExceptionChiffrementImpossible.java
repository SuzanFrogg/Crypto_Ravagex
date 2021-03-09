/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import algorithmes.chiffrement.Algorithme;

/**
 *
 * @author simonetma
 */
public class ExceptionChiffrementImpossible extends ExceptionCryptographie {
    public ExceptionChiffrementImpossible(Algorithme algorithme) {
        super("Chiffrement impossible", "Impossible de crypter avec l'algorithme : "+algorithme.getNom());
    } 
}
