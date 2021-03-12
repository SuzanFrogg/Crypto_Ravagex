package donnees.cles;

import donnees.MotBinaire;
import exceptions.ExceptionConversionImpossible;

/**
 * Description de la classe
 * @author Matthieu
 */
public class CleBinaire implements Cle {

    private MotBinaire listeBits;
    
    public CleBinaire(MotBinaire listeBits) {
        this.listeBits = listeBits;
    }
    
    @Override
    public String asString() throws ExceptionConversionImpossible {
        return this.listeBits.asString();
    }

    @Override
    public int asInteger() throws ExceptionConversionImpossible {
        return this.listeBits.asInteger();
    }

    @Override
    public MotBinaire asMotBinaire() throws ExceptionConversionImpossible {
        return this.listeBits;
    }

}
