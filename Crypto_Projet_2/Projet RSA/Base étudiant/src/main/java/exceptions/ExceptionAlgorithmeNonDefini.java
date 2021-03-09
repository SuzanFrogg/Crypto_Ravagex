/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import entites.Personne;

/**
 *
 * @author simonetma
 */
public class ExceptionAlgorithmeNonDefini extends ExceptionCryptographie {
    public ExceptionAlgorithmeNonDefini(Personne personne) {
        super("Algorithme non défini",personne.getNom()+" ne dispose pas d'algorithme");
    }
}
