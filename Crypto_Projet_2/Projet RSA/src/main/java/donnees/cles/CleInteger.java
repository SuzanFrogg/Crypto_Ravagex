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
public class CleInteger implements Cle{

    private Integer cle;
    
    public CleInteger(Integer cle) {
        this.cle = cle;
    }
    
    @Override
    public String asString() throws ExceptionConversionImpossible {
        return cle.toString();
    }

    @Override
    public int asInteger() throws ExceptionConversionImpossible {
        return cle;
    }

    @Override
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible {
        throw new ExceptionConversionImpossible("CleInteger vers Mot Binaire");
    }
    
}
