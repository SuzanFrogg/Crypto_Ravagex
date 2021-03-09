/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author simonetma
 */
public class ExceptionConversionImpossible extends ExceptionCryptographie {
    
    public ExceptionConversionImpossible(String message) {
        super("Conversion impossible", "Impossible d'effectuer la conversion demandée : ");
    }
    
}
